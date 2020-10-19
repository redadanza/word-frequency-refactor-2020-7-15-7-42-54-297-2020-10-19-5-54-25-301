import java.util.*;


public class WordFrequencyGame {

    public static final String WHITE_SPACES = "\\s+";

    public String getResult(String inputStr){


        if (isSingleWord(inputStr)) {
            return inputStr + " 1";
        } else {
            try {

                List<WordInfo> wordInfoList = getDividedWordInfo(inputStr);
                //get the map for the next step of sizing the same word
                Map<String, List<WordInfo>> map =getListMap(wordInfoList);

                List<WordInfo> uniqueWordInfos = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()){
                    WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
                    uniqueWordInfos.add(wordInfo);
                }
                wordInfoList = uniqueWordInfos;

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

    private  List<WordInfo> getDividedWordInfo(String inputStr){
       String[] words = inputStr.split(WHITE_SPACES);

       List<WordInfo> wordInfoList = new ArrayList<>();
       for (String word : words) {
          WordInfo input = new WordInfo(word, 1);
          wordInfoList.add(input);
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
