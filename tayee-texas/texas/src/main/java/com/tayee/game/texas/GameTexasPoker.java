package com.tayee.game.texas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 德州扑克算法
 * @author zjiang
 */
public class GameTexasPoker {
	
	private final byte[] _lock =new byte[0];
	
	private final Random random = new Random();
	
	//系统牌库
	private final List<int[]> systemPokers = new ArrayList<int[]>(52);
	//公共牌组
	private final List<int[]> openPokers =new ArrayList<int[]>(5);
	
	/**洗牌重置*/
	public void shuffle(){
		synchronized (_lock){
			systemPokers.clear();
			for(int hs=1;hs<=4;hs++){
				for(int ds=1;ds<=13;ds++){
					systemPokers.add(new int[]{hs,ds});
				}
			}
		}
	}
	
	/**随机发公共牌*/
	public int[] deingOpenPoker(){
		int [] p = deing();
		synchronized(_lock){
			openPokers.add(p);
			return p;
		}
	}
	
	/**随机发牌*/
	public int[] deing(){
		synchronized(_lock){
			int index =random.nextInt(systemPokers.size());
			return systemPokers.remove(index);
		}
	}
	

	//测试程序
	public static void main(String[] args) {
		GameTexasPoker tesas =new GameTexasPoker();
		
		tesas.shuffle();
		
		tesas.deingOpenPoker();
		tesas.deingOpenPoker();
		tesas.deingOpenPoker();
		tesas.deingOpenPoker();
		tesas.deingOpenPoker();
		
		for(int[]ss:tesas.openPokers){
			System.out.println("open poker:"+ss[0]+" "+ss[1]);
		}
		
		List<int[]> player1 = new ArrayList<int[]>();
		player1.add(tesas.deing());
		player1.add(tesas.deing());
		
		
		List<int[]> player2 = new ArrayList<int[]>();
		player2.add(tesas.deing());
		player2.add(tesas.deing());
		
		List<int[]> p1 =tesas.getMaxPoker(player1);
		List<int[]> p2 =tesas.getMaxPoker(player2);
		
		for(int[] sp1 :p1){
			System.out.print(sp1[0]+" "+sp1[1]+" ,");
		}
		System.out.println("");
		System.out.println("=============");
		for(int[] sp2 :p2){
			System.out.print(sp2[0]+" "+sp2[1]+" ,");
		}
		int s =pokerPK(p1,p2);
		System.out.println("rs:"+s);
	}
	
	
	/**手牌结合公共牌，获取最大牌组*/
	public List<int[]> getMaxPoker(List<int[]> sp){
		List<int[]> tempMax =new ArrayList<int[]>();
		//可选的公共牌索引
		List<int[]> posArr= arrangePoker();
		for (int[] pos : posArr) {
			//默认第一次牌组为最大
			if(tempMax.isEmpty()){
				//加入手牌
				for(int[] p:sp){
					tempMax.add(p);
				} 
				//加入公共牌
				tempMax.add(openPokers.get(pos[0]));
				tempMax.add(openPokers.get(pos[1]));
				tempMax.add(openPokers.get(pos[2]));
			}else{
				//
				List<int[]> newPs =new ArrayList<int[]>();
				//加入手牌
				for(int[] p:sp){
					newPs.add(p);
				} 
				//加入公共牌
				newPs.add(openPokers.get(pos[0]));
				newPs.add(openPokers.get(pos[1]));
				newPs.add(openPokers.get(pos[2]));
				//比较tempMax牌组 和newPs牌组大小
				int val =pokerPK(newPs,tempMax);
				if(val==1){
					tempMax.clear();
					tempMax.addAll(newPs);
				}
			}
		}
		return tempMax;
	}
	
	
	/**
	 * 获取当前所有的公共牌组合
	 * return 这里返回的是公共牌索引
	 * */
	public static List<int[]> arrangePoker(){
		List<int[]> indexs =new ArrayList<int[]>();
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				for(int k=0;k<5;k++){
					if(i!=j && j!=k && k!=i){
						int [] tp =new int[]{i,j,k};
						Arrays.sort(tp);
						if(!isContains(indexs,tp)){
							indexs.add(tp);
						}
					}
				}
			}
		}
		
		return indexs;
	}
	
	/**查看指定牌中是否已经包含了当前牌组*/
	public static boolean isContains(List<int[]> ps,int [] p){
		for (int[] temp : ps) {
			if(temp[0]==p[0] && temp[1]==p[1] && temp[2]==p[2]){
				return true;
			}
		}
		return false;
	}
	
	/**比较两个牌组大小(1=大;0=平;-1=小)*/
	public static final int pokerPK(List<int[]> ps1,List<int[]> ps2){
		int p1 = getPokerType(ps1);
		int p2 = getPokerType(ps2);
		if(p1!=p2){
			//牌型不同
			return (p1>p2)?1:-1;
		}else{			
			int s1 = pointSum(ps1);
			int s2 = pointSum(ps2);
			//牌型相同,比较总点数，点数相同返回0
			return (s1!=s2)?((s1>s2)?1:-1):0;
		}
	}
	
	/**获取牌型*/
	public static final int getPokerType(List<int[]> ps){
		if(isStraightFlush(ps)){
			return StraightFlush;
		}
		if(isFourKind(ps)){
			return FourKind;
		}
		if(isThreadKindAndPair(ps)){
			return ThreeKindPair;
		}
		if(isFlush(ps)){
			return Flush;
		}
		if(isStraight(ps)){
			return Straight;
		}
		if(isThreeKind(ps)){
			return ThreeKind;
		}
		if(isDoublePair(ps)){
			return DoublePair;
		}
		if(isSinglePair(ps)){
			return SinglePair;
		}
		return SinglePoint;
	}
	
	/**判断是否有四条*/
	public static final  boolean isFourKind(List<int[]> ps){
		for(int i=0;i>ps.size();i++){
			if(alikePoint(ps,ps.get(i),4)){
				return true;
			}
		}
		return false;
	}
	
	/**判断是否有三条带对*/
	public static final  boolean isThreadKindAndPair(List<int[]> ps){
		Set<Integer> counter=new HashSet<Integer>();
		//存在三条则加入set
		for(int i=0;i<ps.size();i++){
			if(alikePoint(ps,ps.get(i),3)){
				counter.add(ps.get(i)[1]);
			}
		}
		//存在对加入set
		for(int i=0;i<ps.size();i++){
			if(alikePoint(ps,ps.get(i),2)){
				counter.add(ps.get(i)[1]);
			}
		}
		return counter.size()==2;

	}
	
	/**判断是否有三条*/
	public static final  boolean isThreeKind(List<int[]> ps){
		for(int i=0;i<ps.size();i++){
			if(alikePoint(ps,ps.get(i),3)){
				return true;
			}
		}
		return false;
	}
	
	/**判断是否有连对*/
	public static final  boolean isDoublePair(List<int[]> ps){
		Set<Integer> counter=new HashSet<Integer>();
		for(int i=0;i<ps.size();i++){
			if(alikePoint(ps,ps.get(i),2)){
				int point = ps.get(i)[1];
				if(!counter.contains(point)){
					counter.add(point);
				}
			}
		}
		return counter.size()==2;
	}
	
	/**判断是否有单对*/
	public static final  boolean isSinglePair(List<int[]> ps){
		for(int i=0;i<ps.size();i++){
			if(alikePoint(ps,ps.get(i),2)){
				return true;
			}
		}
		return false;
	}
	
	/**判断是否为同花顺*/
	public static final  boolean isStraightFlush(List<int[]> ps){
		return isFlush(ps) && isStraight(ps);
	}
	
	/**判断是否顺子*/
	public static final  boolean isStraight(List<int[]> ps){
		//按大小排序
		ps.sort(SortComparator);
		//如果int[i]减去int[i-1]的差值不为1,则返回false
		for(int i=0;i<ps.size();i++){
			if(i!=0){
				int val = ps.get(i)[1]-ps.get(i-1)[1];
				if(val!=1){
					return false;
				}
			}
		}
		return true;
	}
	
	/**判断是否同花*/
	public static final  boolean isFlush(List<int[]> ps){
		int _hs=0;
		for(int i=0;i<ps.size();i++){
			if(i==0){
				_hs = ps.get(i)[0];
			}else{
				if(_hs!=ps.get(i)[0]){
					return false;
				}
			}
		}
		return true;
	}
	
	/**计算牌组总点数*/
	public static final int pointSum(List<int[]> ps){
		int val =0;
		for (int i = 0; i < ps.size(); i++) {
			val+=ps.get(i)[1];
		}
		return val;
	}
	
	/**判断指定牌点是否有重复*/
	public static final boolean alikePoint(List<int[]> ps,int[] p,int size){
		int counter = 0;
		for(int i=0;i<ps.size();i++){
			if(ps.get(i)[1] == p[1]){
				counter+=1;
			}
		}
		return counter==size;
	}
	
	/**牌点排序比较器*/
	public static final Comparator<int[]> SortComparator =  new Comparator<int[]>() {
		public int compare(int[] o1, int[] o2) {
			return (o1[1]>o2[1])?1:0;
		}
	};	
	
	/**单牌*/
	public static final int SinglePoint = 1;
	/**单对*/
	public static final int	SinglePair =2;
	/**双对*/
	public static final int DoublePair =3;
	/**三条*/
	public static final int ThreeKind =4;
	/**顺子*/
	public static final int Straight =5;
	/**同花*/
	public static final int Flush =6;
	/**葫芦*/
	public static final int ThreeKindPair =7;
	/**四条*/
	public static final int FourKind = 8;
	/**同花顺*/
	public static final int StraightFlush = 9;
	
}
