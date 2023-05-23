import java.io.*;
import java.util.*;

public class DnaResults {
    // main() method
    // input parameters:	none
    // return value:		none
    //
    // Purpose:				Runs the main program
    private static String[] rsids = new String[1000000];
    private static String[] genotypes = new String[1000000];
    public static void main(String[] args) throws IOException {
        File f = new File("dna1.txt");
        String fileName = f.getName();
        System.out.println("Parsing " + fileName);
        String[] arr = parseFile(f);
        System.out.println(checkSkinType(arr));
        System.out.println(checkType2Diabetes(arr));
        Scanner scan = new Scanner(System.in);
        Arrays.fill(rsids, "");
        dietRecommendation();
    }
    // parseFile() method
    // input parameters:	filename (name of data file to be read)
    // return value:		none
    //
    // Purpose:				reads file and fills in the rsids[] and genotypes[] arrays
    private static String[] parseFile(File f) throws IOException {
        ArrayList<String> stringList = new ArrayList<>();
        String str;
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            str = s.nextLine();
            stringList.add(str);
        }
        String[] arr = stringList.toArray(new String[0]);
        return arr;
    }
    // checkSkinType() method
    // input parameters:	rsids array, genotype array
    // return value:		String
    //
    // Purpose:				scans the rsids and genotypes arrays to find skin type
    //							AA 	Probably light-skinned, European ancestry
    //							AG 	Probably mixed African/European ancestry
    //							GG 	Probably darker-skinned, Asian or African ancestry
    //							** 	No DNA info on Skin Type
    private static String checkSkinType(String[] arr) throws FileNotFoundException {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].startsWith("rs1426654")) {
                if (arr[i].endsWith("AA")) {
                    return "Probably light-skinned, European ancestry";
                }
                if (arr[i].endsWith("AG")) {
                    return "Probably mixed African/European ancestry";
                }
                if (arr[i].endsWith("GG")) {
                    return "Probably darker-skinned, Asian or African ancestry";
                } else {
                    return "No DNA info on Skin Type";
                }
            }
        }
        return "rs1426654 not found";
    }
    // checkType2Diabetes
    // input parameters:	rsids array, genotype array
    // return value:		String
    //
    // Purpose:				scans the rsids and genotypes arrays to find diabetes risk
    //							CG	1.3x Increased risk for Type-2 Diabetes
    //							CC	1.3x Increased risk for Type-2 Diabetes
    //							GG	Normal risk for	Type-2	Diabetes
    //							**	No DNA info on Type-2 Diabetes
    private static String checkType2Diabetes(String[] arr) throws FileNotFoundException {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].startsWith("rs7754840")) {
                if (arr[i].endsWith("CG")) {
                    return "1.3x Increased risk for Type-2 Diabetes";
                }
                if (arr[i].endsWith("CC")) {
                    return "1.3x Increased risk for Type-2 Diabetes";
                }
                if (arr[i].endsWith("GG")) {
                    return "Normal risk for Type-2 Diabetes";
                } else {
                    return "No DNA info on Type-2 Diabetes";
                }
            }
        }
        return "rs7754840 not found";
    }
    private static void dietRecommendation(){
        int i = 0;
        String rs4994 = "";
        String rs1042713 = "";
        String rs1801282 = "";
        String rs1042714 = "";
        String rs1799883 = "";
        while(!rsids[i].equals("")){
            if(rsids[i].equals("rs4994")){
                rs4994 = genotypes[i];
            }else if(rsids[i].equals("rs1042713")){
                rs1042713 = genotypes[i];
            }else if(rsids[i].equals("rs1801282")){
                rs1801282 = genotypes[i];
            }else if(rsids[i].equals("rs1042714")){
                rs1042714 = genotypes[i];
            }else if(rsids[i].equals("rs1799883")){
                rs1799883 = genotypes[i];
            }
            i++;
        }

        if(rs4994.equals("AA") || rs4994.equals("TT")){
            if(rs1042713.equals("AA") || rs1042713.equals("TT")){
                System.out.println("[12%] Genetic Privilege: Any Exercise Works For You");
                if(rs1801282.equals("CC") && rs1042714.equals("CC")){
                    System.out.println("[16%] Genetic Privilege: Any Diet Works For You.");
                }else{
                    System.out.println("[39%] Genetic Disprivilege: You Will Lose 2.5x As Much Weight on a Low Fat Diet");
                }
            }else{
                System.out.println("[88%] Genetic Disprivilege: Only High Intensity Exercise Will Help You Lose Weight");
            }
        }else{
            System.out.println("[88%] Genetic Disprivilege: Only High Intensity Exercise Will Help You Lose Weight");
            if(rs1799883.equals("GG")){
                if(rs1801282.equals("CC") && rs1042714.equals("CC")){
                    System.out.println("[16%] Genetic Privilege: Any Diet Works For You.");
                }else{
                    System.out.println("[39%] Genetic Disprivilege: You Will Lose 2.5x As Much Weight on a Low Fat Diet");
                }
            }else{
                if(rs1801282.equals("CC")){
                    System.out.println("[39%] Genetic Disprivilege: You Will Lose 2.5x As Much Weight on a Low Fat Diet");
                }else{
                    System.out.println("[45%] Genetic Disprivilege: You Will Lose 2.5x As Much Weight on a Low Carb Diet");
                }
            }
        }
    }
}
