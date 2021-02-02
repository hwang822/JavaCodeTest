import java.util.HashMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.*;
import java.util.*;

class Solution { // total 50 solved


    static class ShareData {
        long time;
        long maxTimeGap;
        long volume;
        long weightedAveragePrice;
        long maxPrice;

        public ShareData(long time, long volume, long price){
            this.time = time;
            this.volume = volume;
            this.maxPrice = price;
        }
        public void upDate(long time, long volume, long price){
            if((time-this.time)>this.maxTimeGap)
                this.maxTimeGap = time-this.time;
            this.time = time;
            this.volume += volume;
            this.weightedAveragePrice += price*volume;
            if(price>this.maxPrice)
                this.maxPrice = price;
        }

    }

    public static void main(String[] args) throws IOException {
        HashMap<String, ShareData> tradeMap = new HashMap<>();

        BufferedReader csvReader = new BufferedReader(new FileReader("c:\\input.csv"));
        String row = "";
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            String symbol = data[1];
            long time = Long.parseLong(data[0]);
            long volume = Integer.parseInt(data[2]);
            long price = Integer.parseInt(data[3]);


            if(tradeMap.containsKey(symbol)){
                ShareData tradedata  = tradeMap.get(symbol);
                tradedata.upDate(time, volume, price);
                tradeMap.put(symbol,tradedata);
            }
            else{
                ShareData tradedata = new ShareData(time, volume, price);
                tradeMap.put(symbol,tradedata);
            }

        }
        csvReader.close();

        TreeMap<String, ShareData> sorted = new TreeMap<>();

        // Copy all data from hashMap into TreeMap
        sorted.putAll(tradeMap);

        PrintWriter cvsWriter = new PrintWriter(new File("c:\\workarea\\output.csv"));

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, ShareData> entry : sorted.entrySet()){
            String symbol = entry.getKey();
            ShareData tradedata  = entry.getValue();

            sb.append(symbol);
            sb.append(',');
            sb.append(Long.toString(tradedata.maxTimeGap));
            sb.append(',');
            sb.append(Long.toString(tradedata.volume));
            sb.append(',');
            sb.append(Long.toString(tradedata.weightedAveragePrice/tradedata.volume));
            sb.append(',');
            sb.append(Long.toString(tradedata.maxPrice));
            sb.append(',');
            sb.append('\n');

        }
        cvsWriter.write(sb.toString());
        cvsWriter.close();
    }
}
