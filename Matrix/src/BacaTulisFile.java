import java.io.*;
import java.util.*;

public class BacaTulisFile {
	public static int CVar = 0, NPers = 0;
	public static double[][] M = new double[100][100];
	public static String[] listVar = new String[100];


	// Method tulis file
	public static void tulisFile(String text, String namaFile) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(namaFile, true)));
			out.println(text);
			out.close();
		} catch (IOException e) {
			System.out.println("Gagal menulis isi File!");
			e.printStackTrace();
		}
	}

	// Method baca file
	public static String bacaFile(String namaFile) {
		BufferedReader br = null;
		String stringHasil = "";

		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(namaFile));
			while ((sCurrentLine = br.readLine()) != null) {
				stringHasil = stringHasil + sCurrentLine + "\n";
			}
		} catch (IOException e) {
			System.out.println("Gagal membaca File " + namaFile);
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return stringHasil;
	}

	// Method parsing 
	public static void parsing(String hasil) {
		String[] temp = hasil.split("\n");
		Double[] tempD = new Double[100];
		String[] tempVar = new String[100];
		Double[] tempHasil = new Double[100];
		Boolean bFound = false;
		int NVar = 0, count;

		//Inisialisasi Variabel
		for(int i=0; i<=99; i++) {
			for(int j=0; j<=99; j++) {
				M[i][j] = 0.0;
			}
		}

		for(int i=0; i<=25; i++) {
			listVar[i] = "-";
		}

		NPers = 0;
		CVar = 0;

		//Pencarian NPers
		for (int i = 0; i < hasil.length(); i++) {
			if (hasil.charAt(i) == '=') {
				NPers++;
			}
		}

		//Menyalin hasil parsing ke dalam matriks
		for (int i = 0; i < NPers; i++) {
			String[] pars = temp[i].split("((?<=[a-z])|(?=[a-z]))");

			//Menghitung jumlah variabel yang berada di satu persamaan
			NVar = 0;
			while (pars[NVar].charAt(0) != '=') {
				NVar++;
			};
			NVar = NVar / 2;

			//Menyalin dari array pars ke array yang sesuai
			for (int j = 0; j < NVar; j++) {
				tempD[j] = Double.parseDouble(pars[j*2].replaceAll("(\\+)",""));
				tempVar[j] = pars[j*2+1];
			}

			//Menyalin hasil persamaan di array penampungan sementara
			tempHasil[i] = Double.parseDouble(pars[NVar*2].replaceAll("(\\=)",""));

			//Pencarian variabel yang berada di dalam array listVar
			//Apabila variabel ditemukan, maka nilai akan disalin ke indeks kolom matriks bersangkutan
			//Apabila tidak ditemukan, maka variabel baru akan disalin ke dalam array listVar
			for (int j = 0; j < NVar; j++) {
				int k = 0;
				bFound = false;

				while (!bFound && (k < CVar)) {
					if (tempVar[j].charAt(0) == listVar[k].charAt(0)) {bFound = true;};
					k++;
				}
				
				k--;
				if (bFound) {
					M[i][k] += tempD[j];
				} else {
					listVar[CVar] = tempVar[j];
					M[i][CVar] += tempD[j];
					CVar++;
				}
			}


		}

		for (int i = 0; i < NPers; i++) {
			M[i][CVar] = tempHasil[i];
		}
	}

	// Method menampilkan menu input user
	public static void menuInput() {
		String[] equation =  new String[1000];
		String namaFile;
		String totalEq = "";
		int NPers;
		int pil;
		Scanner scan = new Scanner(System.in);

		System.out.println("Masukan pilihan input : ");
		System.out.println("1. Konsol");
		System.out.println("2. File Eksternal");
		System.out.print("Pilihan (1/2) : ");
		pil = scan.nextInt();
		scan.nextLine();
		if (pil == 1) {
			System.out.println("Masukan banyak persamaan : ");
			NPers = scan.nextInt();
			scan.nextLine();
			System.out.println("Masukan persamaan : ");
				for (int i=0; i<NPers; i++) {
				equation[i] = scan.nextLine();
				totalEq = totalEq + equation[i] + "\n";
			}
				BacaTulisFile.parsing(totalEq);

		} else if (pil == 2) {
			System.out.print("Masukan nama file : ");
			namaFile = scan.nextLine();
			String hasil = BacaTulisFile.bacaFile(namaFile);
			System.out.println("Persamaan dalam File : ");
			System.out.println(hasil);
			BacaTulisFile.parsing(hasil);
		} else {
			System.out.println("Input harus antara 1 atau 2!");
		}
	}

	public static int GetBaris(){
		return NPers;
	}

	public static int GetKolom(){
		return CVar;
	}

	public static double[][] GetMatriks() {
		return M;
	}

	public static String GetListVar(int i) {
		return listVar[i];
	}

}