import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class WordFrequencyGame {

    public static final String WHITE_SPACES = "\\s+";

    public String getResult(String inputStr){


        if (isSingleWord(inputStr)) {
            return inputStr + " 1";
        } else {
            try {
                List<WordInfo> wordInfoList = getWordInfoFrequency(inputStr);
                return getFinalOutput(wordInfoList);

            } catch (Exception e) {
                return "Calculate Error";
            }

        }
    }

    private String getFinalOutput(List<WordInfo> wordInfoList){
        wordInfoList.sort((currentWord, nextWord) -> nextWord.getWordCount() - currentWord.getWordCount());

        StringJoiner joiner = new StringJoiner("\n");
        wordInfoList.stream().map(wordInfo -> wordInfo.getValue() + " " + wordInfo.getWordCount()).forEach(joiner::add);
       return  joiner.toString();
    }
    private  List<WordInfo> getWordInfoFrequency(String inputStr){

        List<String> words = Arrays.asList(inputStr.split(WHITE_SPACES));
        List<WordInfo> wordInfoList = new ArrayList<>();
        words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((word, count) -> wordInfoList.add(new WordInfo(word, Math.toIntExact(count))));


        return wordInfoList;

    }
    private boolean isSingleWord(String inputStr) {
        return inputStr.split(WHITE_SPACES).length==1;
    }

}
