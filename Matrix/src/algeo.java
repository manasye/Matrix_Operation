import java.util.*;
import java.lang.*;
import java.io.*;

public class algeo {

    // Deklarasi Nilai EPSILON Untuk Mengatasi Galat Floating Point
    private static final double EPSILON = 3.1401849173675503e-16;

// Deklarasi Nilai EPSILON Untuk Mengatasi Galat Floating Point
    private static final double EPSILON2 = 3.1401849173675503e-32;
    // Prosedur Mencetak Matriks
    private static void printmatriks(double[][] A, double[] b,int m,int n){
        for (int i = 0; i < m; i++) {
            for(int j=0;j < n;j++) {
                // Mengatasi A[i][j]=-0.00
                if(A[i][j]==0){
                    A[i][j]=0;
                }
                System.out.printf("%.2f",A[i][j]);
                System.out.printf("  ");
            }
            System.out.printf("%.2f",b[i]);
            System.out.println();
        }

    }

    // Mengecek Apakah Baris Mengandung 0 Semua
    private static boolean isZero(double[][] A,double[]b, int i){
        boolean cek;
        cek=true;
        int n=A[0].length;
        for(int j=0;j<n;j++){
            if(A[i][j]!=0){
                cek=false;
            }
            if(b[i]!=0){
                cek=false;
            }
        }
        return cek;
    }

    // Mengecek Apakah Baris Mengandung 0 Semua Dan Solusi Bukan 0
    private static boolean isZeroSatu(double[][] A,double[]b, int i){
        boolean cek2 = true;
        boolean cek1 = true;
        int n=A[0].length;
        for(int j=0;j<n;j++){
            if(A[i][j]!=0){
                cek1=false;
            }
            if(b[i]==0){
                cek2=false;
            }
        }
        return (cek1&&cek2);
    }

    // Mengecek Apakah Matriks Mengandung Baris Yang Mengandung 0 Semua
    private static boolean isMatriksZero(double[][] A,double []b){
        boolean cek;
        cek=false;
        int m=A.length;
        for(int j=0;j<m;j++){
            if(isZero(A,b,j)){
                cek=true;
            }
        }
        return cek;
    }

    // Mengecek Apakah Matriks Mengandung Baris Yang Mengandung 0 Semua Dan Solusi Bukan 0
    private static boolean isMatriksZeroSatu(double[][] A,double []b){
        boolean cek;
        cek=false;
        int m=A.length;
        for(int j=0;j<m;j++){
            if(isZeroSatu(A,b,j)){
                cek=true;
            }
        }
        return cek;
    }

    // Eliminasi Gauss Dengan Pivoting
    private static void solvegauss(double[][] A, double[] b,int m,int n) {
        for (int p = 0; p < n; p++) {

            // Mencari Maks Per Kolom Dan Menukarnya
            int max = p;

            for (int i = p + 1; i < m; i++)
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }

            //Menukar Variabel Max Ke Atas
            double[] temp = A[p];
            A[p] = A[max];
            A[max] = temp;

            //Menukar Solusi Max Ke Atas
            double t = b[p];
            b[p] = b[max];
            b[max] = t;

            // OBE Per Kolom
            if(A[p][p]!=0) {
                for (int i = p + 1; i < m; i++) {
                    double factor = A[i][p] / A[p][p];
                    b[i] -= factor * b[p];
                    for (int j = p; j < m; j++) {
                        A[i][j] -= factor * A[p][j];
                    }
                }
            }

        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(A[i][j]==0){
                    A[i][j]=0;
                }
            }
        }


        for(int i=0;i<m;i++){
            if(!isZero(A,b,i)&&(!isZeroSatu(A,b,i))) {
                int temp=0;
                for (int j = 0; j < n; j++) {
                    if (!(Math.abs(A[i][j]) <= EPSILON)) {
                        temp = j;
                        break;
                    }
                }
                double factor = A[i][temp];
                b[i] = b[i] / factor;
                for (int j = 0; j < n; j++) {
                    A[i][j] = A[i][j] / factor;
                    if ((Math.abs(A[i][j]) <= EPSILON)) {
                        A[i][j]=0;
                    }
                }
            }
        }

        System.out.println("\nHasil OBE Gauss :");
        printmatriks(A,b,m,n);
    }


    private static void solvegausshilbert(double[][] A, double[] b,int m,int n) {
        for (int p = 0; p < n; p++) {

            // Mencari Maks Per Kolom Dan Menukarnya
            int max = p;

            for (int i = p + 1; i < m; i++)
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }

            //Menukar Variabel Max Ke Atas
            double[] temp = A[p];
            A[p] = A[max];
            A[max] = temp;

            //Menukar Solusi Max Ke Atas
            double t = b[p];
            b[p] = b[max];
            b[max] = t;

            // OBE Per Kolom
            if(A[p][p]!=0) {
                for (int i = p + 1; i < m; i++) {
                    double factor = A[i][p] / A[p][p];
                    b[i] -= factor * b[p];
                    for (int j = p; j < m; j++) {
                        A[i][j] -= factor * A[p][j];
                    }
                }
            }

        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(A[i][j]==0){
                    A[i][j]=0;
                }
            }
        }


        for(int i=0;i<m;i++){
            if(!isZero(A,b,i)&&(!isZeroSatu(A,b,i))) {
                int temp=0;
                for (int j = 0; j < n; j++) {
                    if (!(Math.abs(A[i][j]) <= EPSILON2)) {
                        temp = j;
                        break;
                    }
                }
                double factor = A[i][temp];
                b[i] = b[i] / factor;
                for (int j = 0; j < n; j++) {
                    A[i][j] = A[i][j] / factor;
                    if ((Math.abs(A[i][j]) <= EPSILON2)) {
                        A[i][j]=0;
                    }
                }
            }
        }

        System.out.println("\nHasil OBE Gauss :");
        printmatriks(A,b,m,n);
    }

    // Eliminasi Gauss-Jordan Dengan Pivoting
    private static void solvegaussjordan(double[][] A, double[] b,int m,int n) {


        for (int p = 0; p < n; p++) {

            // Mencari Maks Per Kolom Dan Menukarnya
            int max = p;

            for (int i = p + 1; i < m; i++)
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }

            //Menukar Variabel Max Ke Atas
            double[] temp = A[p];
            A[p] = A[max];
            A[max] = temp;

            //Menukar Solusi Max Ke Atas
            double t = b[p];
            b[p] = b[max];
            b[max] = t;

            // OBE Per Kolom
            if(A[p][p]!=0) {
                for (int i = p + 1; i < m; i++) {
                    double factor = A[i][p] / A[p][p];
                    b[i] -= factor * b[p];
                    for (int j = p; j < m; j++) {
                        A[i][j] -= factor * A[p][j];
                    }
                }
            }
        }

        for(int i=0;i<m;i++){
            if(!isZero(A,b,i)&&(!isZeroSatu(A,b,i))) {
                int temp = 0;
                for (int j = 0; j < n; j++) {
                    if (!(Math.abs(A[i][j]) <= EPSILON)) {
                        temp = j;
                        break;
                    }
                }
                double factor = A[i][temp];
                b[i] = b[i] / factor;
                for (int j = 0; j < n; j++) {
                    A[i][j] = A[i][j] / factor;
                    if ((Math.abs(A[i][j]) <= EPSILON)) {
                        A[i][j] = 0;
                    }
                }
            }
        }

        for(int i=0;i<m;i++){
            if(!isZero(A,b,i)&&(!isZeroSatu(A,b,i))) {
                int temp=cariSatu(A,i);
                for (int j = 0; j < i; j++) {
                    double factor = A[j][temp] / A[i][temp];
                    for (int k = 0; k < n; k++) {
                        A[j][k] -= (A[i][k] * factor);
                    }
                    b[j] -= (b[i] * factor);
                }
            }
        }
        System.out.println("\nHasil OBE Gauss-Jordan:");
        printmatriks(A,b,m,n);

    }

// Eliminasi Gauss-Jordan Dengan Pivoting
    private static void solvegaussjordanhilbert(double[][] A, double[] b,int m,int n) {


        for (int p = 0; p < n; p++) {

            // Mencari Maks Per Kolom Dan Menukarnya
            int max = p;

            for (int i = p + 1; i < m; i++)
                if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                    max = i;
                }

            //Menukar Variabel Max Ke Atas
            double[] temp = A[p];
            A[p] = A[max];
            A[max] = temp;

            //Menukar Solusi Max Ke Atas
            double t = b[p];
            b[p] = b[max];
            b[max] = t;

            // OBE Per Kolom
            if(A[p][p]!=0) {
                for (int i = p + 1; i < m; i++) {
                    double factor = A[i][p] / A[p][p];
                    b[i] -= factor * b[p];
                    for (int j = p; j < m; j++) {
                        A[i][j] -= factor * A[p][j];
                    }
                }
            }
        }

        for(int i=0;i<m;i++){
            if(!isZero(A,b,i)&&(!isZeroSatu(A,b,i))) {
                int temp = 0;
                for (int j = 0; j < n; j++) {
                    if (!(Math.abs(A[i][j]) <= EPSILON2)) {
                        temp = j;
                        break;
                    }
                }
                double factor = A[i][temp];
                b[i] = b[i] / factor;
                for (int j = 0; j < n; j++) {
                    A[i][j] = A[i][j] / factor;
                    if ((Math.abs(A[i][j]) <= EPSILON2)) {
                        A[i][j] = 0;
                    }
                }
            }
        }

        for(int i=0;i<m;i++){
            if(!isZero(A,b,i)&&(!isZeroSatu(A,b,i))) {
                int temp=cariSatu(A,i);
                for (int j = 0; j < i; j++) {
                    double factor = A[j][temp] / A[i][temp];
                    for (int k = 0; k < n; k++) {
                        A[j][k] -= (A[i][k] * factor);
                    }
                    b[j] -= (b[i] * factor);
                }
            }
        }
        System.out.println("\nHasil OBE Gauss-Jordan:");
        printmatriks(A,b,m,n);

    }
    // Mencari Solusi Simpan Dalam String
    private static String[] solution(double[][] A, double[] b, int m, int n){
        double[] x = new double[m];
        String[] y = new String[m];
        for (int i = m - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
            if (Math.abs(x[i])<=EPSILON) {
                x[i]=0;
            }
            y[i] = Double.toString(x[i]);
        }
        return y;
    }

    // Mencari Solusi Simpan Dalam Integer
    private static double[] solutionint(double[][] A, double[] b, int m, int n){
        double[] x = new double[m];
        for (int i = m - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
            if (Math.abs(x[i])<=EPSILON) {
                x[i]=0;
            }
        }
        return x;
    }

    // Mencari Solusi Yang Berbentuk Parametris
    private static String[] solutionparametris(double[][] A, double[] b, int m, int n) {
        double[] x = new double[n];
        String[] y = new String[n];
        for (int i = 0; i < n; i++) {
            String s = String.valueOf(i+1);
            y[i] = "x"+s;
        }
        for (int i = m - 1; i >= 0; i--) {
            if (!isZero(A, b, i)) {
                if (sendiriSatu(A, i)) {
                    x[cariSatu(A, i)] = b[i];
                    y[(cariSatu(A, i))] = Double.toString(x[cariSatu(A, i)]);
                } else {
                    y[(cariSatu(A, i))] = (String.valueOf(b[i]));
                    for(int j=0; j<n ;j++){
                        if(j!=cariSatu(A,i)){
                            if(A[i][j]!=0) {
                                String s = (String.valueOf(A[i][j])).concat("*").concat(y[j]);
                                y[(cariSatu(A, i))] = y[(cariSatu(A, i))].concat("-(").concat(s).concat(")");
                            }
                        }
                    }
                }
            }
        }
        return y;
    }

    // Mencari 1 Utama Dalam Satu Baris
    private static int cariSatu(double [][] A,int i){
        int x=-999;
        for (int j=0;j<A[0].length;j++){
            if(A[i][j]==1){
                x=j;
                break;
            }
        }
        return x;
    }

    // Mengecek Apakah Di Sebuah Matriks Hanya Ada 1 Sisanya 0
    private static boolean sendiriSatu(double [][] A,int i){
        boolean cek=true;
        for (int j=0;j<A[0].length;j++){
            if(j!=cariSatu(A,i)) {
                if (A[i][j] != 0) {
                    cek = false;
                }
            }
        }
        return cek;
    }

    // Menghitung Hasil Dari Suatu Fungsi
    private static double fungsi(double x){
        return (Math.exp(-x)/(1+Math.sqrt(x)+Math.pow(x,2)));
    }

    // Program Utama
    public static void main(String[] args) {
        int n = 0, m, ans;
        double[][] A;
        double[] b = new double[100];
        double[][] Matriks;
        String tampil = "";
        String nameFile = "";
        int pilTampil;

        Scanner scan = new Scanner(System.in);
        System.out.print("=======================MENU UTAMA=========================\n");
        System.out.print("|1. Pencarian Solusi Persamaan Dengan Metode Gauss       |\n");
        System.out.print("|2. Pencarian Solusi Persamaan Dengan Metode Gauss-Jordan|\n");
        System.out.print("|3. Pencarian Solusi Matriks Hilbert                     |\n");
        System.out.print("|4. Penaksiran Nilai Dengan Polinom Interpolasi          |\n");
        System.out.print("|5. Penaksiran Nilai Fungsi Dengan Polinom Interpolasi   |\n");
        System.out.print("==========================================================\n");
        System.out.print("Pilih Menu Berdasarkan Angka<1-5> :");
        ans = scan.nextInt();

        if ((ans == 1) || (ans == 2)) {

            BacaTulisFile.menuInput();
            m = BacaTulisFile.GetBaris();
            n = BacaTulisFile.GetKolom();


            // Agar Mudah Dibuat Jadi Matriks Persegi Dengan Paksa
            if (m > n) {
                A = new double[m][m];
                b = new double[m];
            } else {
                A = new double[n][n];
                b = new double[n];
            }

            Matriks = BacaTulisFile.GetMatriks();

            // Menerima Inputan Matriks
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = Matriks[i][j];
                }
            }


            // Memerima Inputan Solusi Matriks

            for (int i = 0; i < m; i++) {
                b[i] = Matriks[i][n];
            }

            System.out.println("Matriks tersebut adalah: ");
            printmatriks(A, b, m, n);

            // Matriks Yang Dapat Disolve Harus Matriks Bujursangkar
            if (m == n) {
                if(ans==1){
                    System.out.println("Tampilkan solusi persamaan ke : ");
                    System.out.println("1. Layar saja");
                    System.out.println("2. Layar dan File");
                    System.out.print("Pilih : ");
                    pilTampil = scan.nextInt();
                    scan.nextLine();
                    if (pilTampil == 2) {
                            System.out.print("Masukan tujuan file : ");
                            nameFile = scan.nextLine();
                            System.out.println("Solusi sukses ditulis di file " + nameFile);
                    }
                    solvegauss(A, b, m, n);
                    if(isMatriksZero(A,b)){
                        String [] x = solutionparametris(A,b,m,n);
                        for (int i = 0; i < n; i++) {
                            tampil +="\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i];
                            System.out.println("\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i]);
                        }
                        if (pilTampil == 2) {
                                BacaTulisFile.tulisFile(tampil,nameFile);
                        }
                    }else if(isMatriksZeroSatu(A,b)){
                           System.out.println("\nMatriks tidak memiliki solusi.");
                           if (pilTampil == 2) {
                                BacaTulisFile.tulisFile("Matriks tidak memiliki solusi",nameFile);
                            }
                    }else {
                        String [] x = solution(A, b, m, n);
                        for (int i = 0; i < n; i++) {
                            tampil +="\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i];
                            System.out.println("\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i]);
                        }
                        if (pilTampil == 2) {
                                BacaTulisFile.tulisFile(tampil,nameFile);
                        }
                    }
                }else{
                    System.out.println("Tampilkan solusi persamaan ke : ");
                    System.out.println("1. Layar saja");
                    System.out.println("2. Layar dan File");
                    System.out.print("Pilih : ");
                    pilTampil = scan.nextInt();
                    scan.nextLine();
                    if (pilTampil == 2) {
                            System.out.print("Masukan tujuan file : ");
                            nameFile = scan.nextLine();
                            System.out.println("Solusi sukses ditulis di file " + nameFile);
                    }
                    solvegaussjordan(A, b, m, n);
                    if(isMatriksZero(A,b)){
                        String [] x=solutionparametris(A,b,m,n);
                        for (int i = 0; i < n; i++) {
                            tampil +="\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i];
                            System.out.println("\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i]);
                        }
                        if (pilTampil == 2) {
                                BacaTulisFile.tulisFile(tampil,nameFile);
                        }
                    }else if(isMatriksZeroSatu(A,b)){
                        System.out.println("\nMatriks tidak memiliki solusi.");
                        if (pilTampil == 2) {
                                BacaTulisFile.tulisFile("Matriks tidak memiliki solusi",nameFile);
                        }
                    }else {
                        String [] x = solution(A, b, m, n);
                        for (int i = 0; i < n; i++) {
                            tampil +="\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i];
                            System.out.println("\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i]);
                        }
                        if (pilTampil == 2) {
                                BacaTulisFile.tulisFile(tampil,nameFile);
                        }
                    }
                }
            }

            // Singular Atau Tak Hingga Solusi
            else {
                if(ans==1){
                    System.out.println("Tampilkan solusi persamaan ke : ");
                    System.out.println("1. Layar saja");
                    System.out.println("2. Layar dan File");
                    System.out.print("Pilih : ");
                    pilTampil = scan.nextInt();
                    scan.nextLine();
                    if (pilTampil == 2) {
                            System.out.print("Masukan tujuan file : ");
                            nameFile = scan.nextLine();
                            System.out.println("Solusi sukses ditulis di file " + nameFile);
                    }
                    if(isMatriksZero(A,b)) {
                        solvegauss(A, b, m, n);
                        if(isMatriksZeroSatu(A,b)){
                            System.out.println("\nMatriks tidak memiliki solusi.");
                            if (pilTampil == 2) {
                                BacaTulisFile.tulisFile("Matriks tidak memiliki solusi",nameFile);
                            }
                        }else {
                            String[] x = solutionparametris(A, b, m, n);
                            for (int i = 0; i < n; i++) {
                                tampil +="\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i];
                                System.out.println("\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i]);
                            }
                            if (pilTampil == 2) {
                                BacaTulisFile.tulisFile(tampil,nameFile);
                            }
                        }
                    }else {
                        solvegauss(A, b, m, n);
                        if(isMatriksZeroSatu(A,b)){
                            System.out.println("\nMatriks tidak memiliki solusi.");
                            if (pilTampil == 2) {
                                BacaTulisFile.tulisFile("Matriks tidak memiliki solusi",nameFile);
                            }
                        }else {
                            String[] x = solution(A, b, m, n);
                            for (int i = 0; i < n; i++) {
                                tampil +="\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i];
                                System.out.println("\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i]);
                            }
                            if (pilTampil == 2) {
                                BacaTulisFile.tulisFile(tampil,nameFile);
                            }
                        }
                    }
                }else{
                    System.out.println("Tampilkan solusi persamaan ke : ");
                    System.out.println("1. Layar saja");
                    System.out.println("2. Layar dan File");
                    System.out.print("Pilih : ");
                    pilTampil = scan.nextInt();
                    scan.nextLine();
                    if (pilTampil == 2) {
                            System.out.print("Masukan tujuan file : ");
                            nameFile = scan.nextLine();
                            System.out.println("Solusi sukses ditulis di file " + nameFile);
                    }
                    if(isMatriksZero(A,b)) {
                        solvegaussjordan(A, b, m, n);
                        if(isMatriksZeroSatu(A,b)){
                            System.out.println("\nMatriks tidak memiliki solusi.");
                            if (pilTampil == 2) {
                                BacaTulisFile.tulisFile("Matriks tidak memiliki solusi",nameFile);
                            }
                        }else {
                            String[] x = solutionparametris(A, b, m, n);
                            for (int i = 0; i < n; i++) {
                                tampil +="\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i];
                                System.out.println("\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i]);
                            }
                            if (pilTampil == 2) {
                                BacaTulisFile.tulisFile(tampil,nameFile);
                            }
                        }
                    }else {
                        solvegaussjordan(A, b, m, n);
                        if(isMatriksZeroSatu(A,b)){
                            System.out.println("\nMatriks tidak memiliki solusi.");
                            if (pilTampil == 2) {
                                BacaTulisFile.tulisFile("Matriks tidak memiliki solusi",nameFile);
                            }
                        }else {
                            String[] x = solution(A, b, m, n);
                            for (int i = 0; i < n; i++) {
                                tampil +="\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i];
                                System.out.println("\nSolusi Variabel " +  BacaTulisFile.GetListVar(i) + " adalah " + x[i]);
                            }
                            if (pilTampil == 2) {
                                BacaTulisFile.tulisFile(tampil,nameFile);
                            }
                        }
                    }
                }

            }
        }

        // Pemilihan Menu Matriks Hilbert
        else if(ans==3){
            System.out.print("Masukkan jumlah n yang diinginkan :");
            n=scan.nextInt();
            A = new double[n][n];
            b = new double[n];

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    A[i][j]=1/((double)i+(double)j+1);
                }
            }

            for(int i=0;i<n;i++){
                b[i]=1;
            }

            System.out.println("\nMatriks tersebut adalah: ");
            printmatriks(A,b,n,n);

            System.out.println("\nIngin Menyelesaikan dengan Metode: ");
            System.out.println("1.Metode Gauss ");
            System.out.println("2.Metode Gauss-Jordan ");
            System.out.printf("Pilihan Anda Adalah: ");
            int pil;
            pil=scan.nextInt();
            scan.nextLine();/*---*/
            System.out.println("Tampilkan solusi persamaan ke : ");
            System.out.println("1. Layar saja");
            System.out.println("2. Layar dan File");
            System.out.print("Pilih : ");
            pilTampil = scan.nextInt();
            scan.nextLine();
            if (pilTampil == 2) {
                System.out.print("Masukan tujuan file : ");
                nameFile = scan.nextLine();
                System.out.println("Solusi sukses ditulis di file " + nameFile);
            }
            if(pil == 1){
                solvegausshilbert(A, b, n, n);
                String[] x = solution(A, b, n, n);
                for (int i = 0; i < n; i++) {
                    tampil +="\nSolusi Variabel ke " + "[" + (i + 1) + "]" + " adalah " + x[i];
                    System.out.println("\nSolusi Variabel ke " + "[" + (i + 1) + "]" + " adalah " + x[i]);
                }
                if (pilTampil == 2) {
                     BacaTulisFile.tulisFile(tampil,nameFile);
                }
            }else{
                solvegaussjordanhilbert(A, b, n, n);
                String[] x = solution(A, b, n, n);
                for (int i = 0; i < n; i++) {
                    tampil +="\nSolusi Variabel ke " + "[" + (i + 1) + "]" + " adalah " + x[i];
                    System.out.println("\nSolusi Variabel ke " + "[" + (i + 1) + "]" + " adalah " + x[i]);
                }
                if (pilTampil == 2) {
                                BacaTulisFile.tulisFile(tampil,nameFile);
                }
            }
        }

        // Interpolasi Dengan Data Inputan Dari User
        else if(ans==4){
            int pil;
            String namaFile;
            double[] c = new double[100];
            double[] a = new double[100];

            System.out.println("Masukan pilihan input : ");
            System.out.println("1. Konsol");
            System.out.println("2. File Eksternal");
            System.out.print("Pilihan (1/2) : ");
            pil = scan.nextInt();
            scan.nextLine();

            if (pil == 1) {
                System.out.print("Masukkan jumlah data yang ingin dimasukkan: ");
                n=scan.nextInt();

                for (int i=0;i<n;i++){
                    System.out.println("Masukkan nilai x["+i+"]:");
                    c[i]=scan.nextDouble();
                    System.out.println("Masukkan nilai y["+i+"]:");
                    b[i]=scan.nextDouble();
                }
            } else if (pil == 2) {
                System.out.print("Masukan nama file : ");
                namaFile = scan.nextLine();
                String hasil = BacaTulisFile.bacaFile(namaFile);

                String[] pars = hasil.split("\n");
                n = Integer.parseInt(pars[0]);
                String[] parsx = pars[1].split("\\s");
                String[] parsy = pars[2].split("\\s");

                for (int i=0;i<n;i++){
                    c[i]=Double.parseDouble(parsx[i]);
                    b[i]=Double.parseDouble(parsy[i]);
                }
            }

            A = new double[n][n];               
            for (int i=0;i<n;i++){
                for (int j=0;j<n;j++){
                    if((i==0)&&(j==0)){
                        A[i][j]=1;
                    }else {
                        A[i][j] = Math.pow(c[i], j);
                    }
                }
            }
            solvegauss(A,b,n,n);
            a=solutionint(A,b,n,n);
            System.out.println();
            System.out.println("Tampilkan solusi persamaan ke : ");
            System.out.println("1. Layar saja");
            System.out.println("2. Layar dan File");
            System.out.print("Pilih : ");
            pilTampil = scan.nextInt();
            scan.nextLine();
            if (pilTampil == 2) {
                System.out.print("Masukan tujuan file : ");
                nameFile = scan.nextLine();
                System.out.println("Solusi sukses ditulis di file " + nameFile);
            }
            for(int i=0;i<n;i++){
                tampil +="a["+i+"]:"+a[i] + "\n";
                System.out.println("a["+i+"]:"+a[i]);
            }

            System.out.println();
            int l=1;
            for(int i=0;i<n;i++){
                if(l==1) {
                    tampil += "f(x) = "+a[i];
                    System.out.print("f(x) = "+a[i]);
                    l++;
                }else{
                    if(a[i]>0) {
                        tampil += " + " + a[i] + "x^" + i + " ";
                        System.out.print(" + " + a[i] + "x^" + i + " ");
                    }else if(a[i]<0) {
                        tampil += " - " + -a[i] + "x^" + i + " ";
                        System.out.print(" - " + -a[i] + "x^" + i + " ");
                    }else{
                        continue;
                    }
                }
            }
            tampil += "\n";

            System.out.println();

            System.out.print("\nMasukkan x yang ingin dicari hasil nya: ");
            double x = scan.nextDouble();
            double result = 0;
            for (int i = 0; i < n; i++) {
                if ((i == 0)) result += a[i];
                else result += a[i] * Math.pow(x, i);
            }
            tampil += "Hasil nya adalah: " + result;
            System.out.println("Hasil nya adalah: " + result);
            if (pilTampil == 2) {
                 BacaTulisFile.tulisFile(tampil,nameFile);
            }
        }

        // Interpolasi Fungsi Dengan Selang Dan Jumlah Partisi Berdasarkan Inputan User
        else{
            double a=0,z=0;
            double h;
            int pil;
            String namaFile;

            System.out.println("Masukan pilihan input : ");
            System.out.println("1. Konsol");
            System.out.println("2. File Eksternal");
            System.out.print("Pilihan (1/2) : ");
            pil = scan.nextInt();
            scan.nextLine();

            if (pil == 1) {
                System.out.print("Masukkan awal selang tersebut: ");
                a=scan.nextDouble();
                System.out.print("Masukkan akhir selang tersebut: ");
                z=scan.nextDouble();
                System.out.print("Masukkan jumlah partisi yang diinginkan: ");
                n=scan.nextInt();
            } else if (pil == 2) {
                System.out.print("Masukan nama file : ");
                namaFile = scan.nextLine();
                String hasil = BacaTulisFile.bacaFile(namaFile);

                String[] pars = hasil.split("\n");
                a = Double.parseDouble(pars[0]);
                z = Double.parseDouble(pars[1]);
                n = Integer.parseInt(pars[2]);
            }



            h=(z-a)/((double)n);
            A = new double[n+1][n+1];
            double[] y = new double[n+1];
            double[] c = new double[n+1];
            double[] s = new double[n+1];

            for (int i=0;i<(n+1);i++){
                c[i]=a+((double)i * h);
                y[i]=fungsi(c[i]);
            }

            for (int i=0;i<(n+1);i++){
                for (int j=0;j<(n+1);j++){
                    if((i==0)&&(j==0)){
                        A[i][j]=1;
                    }else {
                        A[i][j] = Math.pow(c[i], j);
                    }
                }
            }

            solvegauss(A,y,(n+1),(n+1));
            s=solutionint(A,y,(n+1),(n+1));
            System.out.println();
            System.out.println("Tampilkan solusi persamaan ke : ");
            System.out.println("1. Layar saja");
            System.out.println("2. Layar dan File");
            System.out.print("Pilih : ");
            pilTampil = scan.nextInt();
            scan.nextLine();
            if (pilTampil == 2) {
                System.out.print("Masukan tujuan file : ");
                nameFile = scan.nextLine();
                System.out.println("Solusi sukses ditulis di file " + nameFile);
            }
            for(int i=0;i<(n+1);i++){
                tampil += "a["+i+"]:"+s[i]+"\n";
                System.out.println("a["+i+"]:"+s[i]);
            }

            System.out.println();
            int l=1;
            for(int i=0;i<(n+1);i++){
                if(l==1) {
                    tampil += "f(x) = "+s[i];
                    System.out.print("f(x) = "+s[i]);
                    l++;
                }else{
                    if(s[i]>0) {
                        tampil += " + " + s[i] + "x^" + i + " ";
                        System.out.print(" + " + s[i] + "x^" + i + " ");
                    }else if(s[i]<0){
                        tampil += " - " + -s[i] + "x^" + i + " ";
                        System.out.print(" - " + -s[i] + "x^" + i + " ");
                    }else{
                        continue;
                    }
                }
            }
            tampil += "\n";
            System.out.println();

            System.out.print("\nMasukkan x yang ingin dicari hasil nya: ");
            double x = scan.nextDouble();
            double result = 0;
            for (int i = 0; i < (n+1); i++) {
                if ((i == 0)) result += s[i];
                else result += s[i] * Math.pow(x, i);
            }
            tampil += "Hasil nya adalah: " + result;
            System.out.println("Hasil nya adalah: " + result);

            if (pilTampil == 2) {
                 BacaTulisFile.tulisFile(tampil,nameFile);
            }
        }
    }

}