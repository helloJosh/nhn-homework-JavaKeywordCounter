import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JavaKeywordCounter {
    private static final Map<String, Integer> store = new HashMap<>();
    private static final ArrayList<String> contents = new ArrayList<String>();
    private static final String[] keywords = {
            "abstract", "continue", "for", "new", "switch", "assert", "default",
            "goto", "package", "synchronized", "boolean", "do", "if", "private",
            "this", "break", "double", "implements", "protected", "throw", "byte",
            "else", "import", "public", "throws", "case", "enum", "instanceof",
            "return", "transient", "catch", "extends", "int", "short", "try",
            "char", "final", "interface", "static", "void", "class", "finally",
            "long", "strictfp", "volatile", "const", "float", "native", "super", "while"
    };

    // private static Long sequence = 0l;
    /**
     * ArrayList<String> contents에 줄단위로 파일 내용 저장 메소드
     * 
     * @param fileName
     */
    public void readFile(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                contents.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 읽은 파일의 문자가 자바 예약어와 일치하는지 확인하는 메소드
     */
    public void countKeyword() {
        for (int i = 0; i < contents.size(); i++) {
            String line = contents.get(i);
            String[] words = line.split("[^a-zA-Z0-9_$]+");
            for (String s : words) {
                for (int j = 0; j < keywords.length; j++) {
                    if (s.equals(keywords[j]))
                        addKeyword(keywords[j]);
                }
            }
        }
    }

    /**
     * Map의 Value의 값을 1 증가시키는 메소드
     * 
     * @param s
     */
    public void addKeyword(String s) {
        if (store.get(s) == null) {
            store.put(s, 1);
        } else {
            int temp = store.get(s);
            store.put(s, temp + 1);
        }
    }

    /**
     * Map에 있는 자바 예약어 개수 출력하는 메소드
     */
    public void printKeywordNumber() {
        Set<String> set = store.keySet();
        for (String s : set) {
            System.out.println("Number of " + s + " keyword : " + store.get(s));
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("usage: java FileDetails Filename");
            return;
        }
        String fileName = args[0];

        JavaKeywordCounter test = new JavaKeywordCounter();

        test.readFile(fileName);
        test.countKeyword();
        test.printKeywordNumber();

    }

}