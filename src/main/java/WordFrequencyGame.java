import java.util.*;


public class WordFrequencyGame {

    public static final String WHITE_SPACES = "\\s+";

    public String getResult(String inputStr){


        if (isSingleWord(inputStr)) {
            return inputStr + " 1";
        } else {
            try {
                List<WordInfo> wordInfoList = getWordInfoFrequency(inputStr);
                wordInfoList.sort((currentWord, nextWord) -> nextWord.getWordCount() - currentWord.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo wordInfo : wordInfoList) {
                    String s = wordInfo.getValue() + " " +wordInfo.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();

            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private  List<WordInfo> getWordInfoFrequency(String inputStr){

        List<String> words = Arrays.asList(inputStr.split(WHITE_SPACES));
        List<WordInfo> wordInfoList = new ArrayList<>();
        for(String word: new HashSet<>(words)){
            int wordCount = Collections.frequency(words,word);
            wordInfoList.add(new WordInfo(word,wordCount));
        }
          return wordInfoList;

    }
    private boolean isSingleWord(String inputStr) {
        return inputStr.split(WHITE_SPACES).length==1;
    }


    private Map<String,List<WordInfo>> getListMap(List<WordInfo> inputList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo input :  inputList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            }

            else {
                map.get(input.getValue()).add(input);
            }
        }


        return map;
    }


}
