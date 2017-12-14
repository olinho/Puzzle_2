package logic;

import java.io.IOException;

public class Searcher {
	int [] tab;
	int N;
	public Searcher(int [] tab) {
		this.tab = new int[tab.length];
		this.tab = tab;
		N = this.tab.length;
	}
	
	public Searcher(int n, int [] tab) {
		N = n;
		this.tab = new int[N];
		this.tab = tab;
	}
	
	public Searcher(int n, String str) {
		N = n;
		this.tab = new int[N];
		parseStringToArrayOfInt(str);
	}
	
	public int[] searchForBestArea() {
		int bP = 0, bS = 0, bE = -1;	// best fragment subsequently: profit, start position, end position
		int cP = 0, cS = 0, cE = 0;		// current fragment features
		int sP;							// current square profit;
		int offset = 0;				// stores the offset value to the last positive value
		int result[] = new int[3];
		
		for (int i=0; i < N; i++) {
			sP  = tab[i];
			if (sP > 0) {
				cP = cP + sP;
				cE = i;
				offset = 0;
			} 
			else if (sP == 0) {
				if (cP == 0) {
					cP = 0;
					cS = i+1;
					cE = i+1;
					offset = 0;
				} else {
					offset = offset + 1;
					cE = i;
				}
			}
			else {
				
				if (cP > bP) {
					bP = cP;
					bS = cS;
					bE = cE - offset; 
				} else {
					if (cP == bP) {
						// compare best and current fragment's sizes
						if ((bE - bS) > (cE - offset - cS)) {
							bP = cP;
							bS = cS;
							bE = cE - offset;
						}
					}
				}
				if (cP + sP > 0){
					cP = cP + sP;
					cE = i+1;
					offset = offset + 1;
				}
				else {
					cP = 0;
					cS = i+1;
					cE = i+1;
					offset = 0;
				}
			}
			if (cP > bP) {
				bP = cP;
				bS = cS;
				bE = cE - offset; 
			} else {
				if (cP == bP) {
					// compare best and current fragment's sizes
					if ((bE - bS) > (cE - offset - cS)) {
						bP = cP;
						bS = cS;
						bE = cE - offset;
					}
				}
			}
		}
		result[0] = bS;
		result[1] = bE;
		result[2] = bP;
		return result;
	}
	
	public void parseStringToArrayOfInt(String str) {
		String[] values = null;
		values = new String[N];
		values = str.split(" ");
		tab = new int[N];
		tab = mapStringTabToIntTab(values);
	}
	
	public int[] mapStringTabToIntTab(String [] strTab) {
		int [] tab = new int[strTab.length];
		for (int i=0; i < strTab.length; i++){
			tab[i] = Integer.parseInt(strTab[i]);
		}
		return tab;
	}
}
