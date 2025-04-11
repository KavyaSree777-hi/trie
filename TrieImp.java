import java.util.*;

class Trie {
    boolean ended;
    int wc;
    Trie[] ch;

    Trie() {
        ch = new Trie[26];
        ended = false;
        wc = 0;
    }
}

public class TrieImp {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Trie root = new Trie();
    boolean running = true;
    
    while (running) {
        System.out.println("1. Insert a word");
        System.out.println("2. Search a word");
        System.out.println("3. Get all words");
        System.out.println("4. Get all words with a prefix");
        System.out.println("5. Exit");
        int ch = sc.nextInt();
        sc.nextLine();
        
        switch (ch) {
            case 1:
                System.out.print("Enter word to insert: ");
                String wordToInsert = sc.nextLine();
                insert(root, wordToInsert);
                System.out.println("Word inserted successfully.");
                break;
                
                case 2:
                System.out.print("Enter word to search: ");
                String wordToSearch = sc.nextLine();
                if (doesExist(root, wordToSearch)) {
                    System.out.println("Word exists in trie.");
                } else {
                    System.out.println("Word NOT found in trie.");
                }
                break;

                case 3:
                System.out.println("All words in the trie:");
                List<String> all = getAllWords(root);
                if (all.isEmpty()) System.out.println("No words found.");
                else for (String w : all) System.out.println(w);
                break;
                
                case 4:
                System.out.print("Enter prefix: ");
                String prefix = sc.nextLine();
                List<String> withPrefix = getWordsWithPrefix(root, prefix);
                if (withPrefix.isEmpty()) System.out.println("No words found with given prefix.");
                else for (String w : withPrefix) System.out.println(w);
                break;
                
                case 5:
                running = false;
                System.out.println("Exiting...");
                break;
                
                default:
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
        static void insert(Trie root, String s) {
            Trie te = root;
            for (char ci : s.toCharArray()) {
                int ind = ci - 'a';
                if (te.ch[ind] == null) {
                    te.ch[ind] = new Trie();
                }
                te = te.ch[ind];
                te.wc++;
            }
            te.ended = true;
        }
        static boolean doesExist(Trie root, String s) {
            Trie te = root;
            for (char ci : s.toCharArray()) {
                int ind = ci - 'a';
                if (te.ch[ind] == null) {
                    return false;
                }
                te = te.ch[ind];
            }
            return te.ended;
        }
        static void help(Trie root, List<String> li, String te) {
            if (root.ended) {
                li.add(te);
            }
            for (int i = 0; i < 26; i++) {
                if (root.ch[i] != null) {
                    char ch = (char) (i + 'a');
                    help(root.ch[i], li, te + ch);
                }
            }
        }
        static List<String> getAllWords(Trie root) {
            List<String> res = new ArrayList<>();
            help(root, res, "");
            return res;
        }
        static List<String> getWordsWithPrefix(Trie root, String prefix) {
            Trie te = root;
            for (char ci : prefix.toCharArray()) {
                int ind = ci - 'a';
                if (te.ch[ind] == null) return new ArrayList<>();
                te = te.ch[ind];
            }
            List<String> res = new ArrayList<>();
            help(te, res, prefix);
            return res;
        }
        
    
}