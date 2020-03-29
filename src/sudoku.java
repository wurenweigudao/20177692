import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;


public class sudoku {
    /*
     * ��ʼ�������в���
     */
    public static String inputFilename;
    public static String outputFilename;
    public static int m;
    public static int n;
    /**
     * �ж��ھŹ����е�����(x,y)��λ���ϲ���value���Ƿ���Ϲ���
     */
    public static Boolean legal(int a[][],int x, int y, int value,int m) {

        for (int i = 0; i < m; i++) {
            //���������value���򷵻�false
            if (i != x && a[i][y] == value) {
                return false;
            }
            //���������value���򷵻�false
            if (i != y && a[x][i] == value) {
                return false;
            }
        }
        if(m==9){
            //(minX,minY)��(x,y)����С�Ź�������Ͻǵ�����
            int minX = x / 3 * 3;
            int minY = y / 3 * 3;
            for (int i = minX; i < minX + 3; i++) {
                for (int j = minY; j < minY + 3; j++) {
                    //���С�Ź����еķ�(x,y)�������ϵ�ֵΪvalue������false
                    if (i != x && j != y && a[i][j] == value) {
                        return false;
                    }
                }
            }
        }
        if(m==4){
            //(minX,minY)��(x,y)����С4��������Ͻǵ�����
            int minX = x / 2 * 2;
            int minY = y / 2 * 2;

            for (int i = minX; i < minX + 2; i++) {
                for (int j = minY; j < minY + 2; j++) {
                    //���С�Ź����еķ�(x,y)�������ϵ�ֵΪvalue������false
                    if (i != x && j != y && a[i][j] == value) {
                        return false;
                    }
                }
            }
        }
        if(m==8){
            //(minX,minY)��(x,y)����С8��������Ͻǵ�����
            int minX = x / 4 * 4;
            int minY = y / 2 * 2;
            for (int i = minX; i < minX + 4; i++) {
                for (int j = minY; j < minY + 2; j++) {
                    //���С�Ź����еķ�(x,y)�������ϵ�ֵΪvalue������false
                    if (i != x && j != y && a[i][j] == value) {
                        return false;
                    }
                }
            }
        }
        if(m==6){
            //(minX,minY)��(x,y)����С��������Ͻǵ�����
            int minX = x / 2 * 2;
            int minY = y / 3 * 3;
            for (int i = minX; i < minX + 2; i++) {
                for (int j = minY; j < minY + 3; j++) {
                    //���С�Ź����еķ�(x,y)�������ϵ�ֵΪvalue������false
                    if (i != x && j != y && a[i][j] == value) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    //�������֣�����Եڶ��������ɵ�������Ϸ��ʹ�û��ݷ���ʵ�ֶ������Ľ��
    //shuDu[][]���������������Ϸ�Ķ�ά���顣
    public static int shuDu[][] = new int[9][9];
    public static void setShuDu(int[][] shuDu) {

        sudoku.shuDu = shuDu;

    }
    //���ݷ�����������ο���һ�����û��ݷ�������������Ľ�ռ�Ĵ���
    /**
     * ���ݷ�����������ο���һ�����û��ݷ�������������Ľ�ռ�Ĵ���

     * @param k

     * @throws IOException

     */
    public static void shuDu_solution(int k,int m) throws IOException {
        if (k == (m*m)) {
            String src= outputFilename;
            try{
                FileWriter fw = new FileWriter(src,true);
                for(int i=0;i<m;i++){
                    for(int j=0;j<m;j++){
                        fw.write(shuDu[i][j]+" ");
                    }
                    fw.write("\r\n");
                }
                fw.write("\r\n");
                fw.close(); // ���ǵùر��ļ�
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        int x = k / m;
        int y = k % m;
        if (shuDu[x][y] == 0) {
            for (int i = 1; i <= m; i++) {
                shuDu[x][y] = i;
                if (legal(shuDu,x, y, i,m)) {
                    shuDu_solution(k + 1,m);
                }
            }
            shuDu[x][y] = 0;
        } else {
            shuDu_solution(k + 1,m);
        }
    }

    public static void loadArgs(String args[]){
        if(args.length>0&&args!=null){
            for(int i=0;i<args.length-1;i++){
                switch (args[i]) {
                    case "-i":
                        inputFilename = args[++i];
                        break;
                    case "-o":
                        outputFilename = args[++i];
                        break;
                    case "-m":
                        m=Integer.valueOf(args[++i]);
                        break;
                    case "-n":
                        n=Integer.valueOf(args[++i]);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        loadArgs(args);
        int generateShuDu[][]=new int[10][10];
        File myFile = new File(inputFilename);
        Reader reader = new InputStreamReader(new FileInputStream(myFile),"UTF-8");
        int tempchar;
        int i=0;
        int j=0;
        while ((tempchar = reader.read()) != -1) {
            if ( (((char) tempchar) != '\n') &&(((char) tempchar) != ' ')) {
                if(i<m){
                    if(j<m){
                        if(tempchar!=13){
                            generateShuDu[i][j]=((char) tempchar)-48;
                            j++;
                        }
                    }else{
                        i++;
                        j=0;
                        generateShuDu[i][j]=((char) tempchar)-48;
                    }
                }
                if(i==m){
                    if(n!=0){
                        setShuDu(generateShuDu);
                        shuDu_solution(0,m);
                        n--;
                        i=0;j=0;
                    }
                }
            }
        }
        reader.close();
    }
}

