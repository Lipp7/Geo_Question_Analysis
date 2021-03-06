package cn.edu.nju.ws.GeoScholar.templating.choice;

import java.util.ArrayList;

import cn.edu.nju.ws.GeoScholar.templating.common.Print;
import cn.edu.nju.ws.GeoScholar.templating.common.Tree;
import cn.edu.nju.ws.GeoScholar.templating.common.findCueWord;

public class FenbuSolution1 {
	
	public static String findFirst(Tree word, ArrayList<String> sentence) {
		Tree t = word.parent.parent;
		String s = "";
		while (!t.parent.content.equals("ROOT") && !((t.parent.content.equals("IP") || t.parent.content.equals("NP") ||
				(t.parent.content.equals("VP") && t.parent.parent.content.equals("ROOT"))) && t.parent.child.size() > 1 && t.parent.child.indexOf(t) > 0)) t = t.parent;
		Tree temp = null;
		for (int i = t.parent.child.indexOf(t) - 1; i >= 0; i--) {
			temp = findCueWord.findFirstNP(t.parent.child.get(i), sentence);
			if (temp != null) break;
		}
		if (temp == null) {
			if (t.parent.child.indexOf(t) == 0)
				return s;
			else
				return Print.print(t.parent.child.get(t.parent.child.indexOf(t) - 1));
		}
		else
			return Print.print(temp);
	}
	
	public static String findSecond(Tree word, ArrayList<String> sentence) {
		Tree t = word.parent.parent; 
		int i = word.no - 2;
		if (i >= 0 && (sentence.get(i).startsWith("如此") || sentence.get(i).startsWith("这样"))) 
			return "如此";
		if (word.no == sentence.size()) return "";
		return t.child.size() > 1 ? Print.print(findCueWord.findFirstNP(t.child.get(1), sentence)) : sentence.get(word.no).split("_")[0];
	}
	
	public static String print(Tree node, ArrayList<String> sentence) {
		return findFirst(node, sentence) + "@" + findSecond(node, sentence);
	}
}
