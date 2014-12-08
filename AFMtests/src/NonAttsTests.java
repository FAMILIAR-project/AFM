import fr.familiar.attributedfm.AttributedFeatureModel;
import fr.familiar.attributedfm.reasoning.ChocoReasoner;
import fr.familiar.attributedfm.reasoning.questions.ChocoDead;
import fr.familiar.readers.VMReader;


public class NonAttsTests {

	public static void main(String[] args) throws Exception {
		
		System.out.println("----------------- TEST 1 --------------------");
		VMReader reader = new VMReader();
		AttributedFeatureModel parseFile = reader.parseFile("FaMaTeS-inputs/case1/df-case1.vm");
		ChocoReasoner reasoner = new ChocoReasoner();
		parseFile.transformto(reasoner);
		ChocoDead dead= new ChocoDead();
		dead.answer(reasoner);
		System.out.println(dead.deads);
		System.out.println("----------------- TEST 2 --------------------");
		reader = new VMReader();
		parseFile = reader.parseFile("FaMaTeS-inputs/case2/df-case2.vm");
		reasoner = new ChocoReasoner();
		parseFile.transformto(reasoner);
		dead= new ChocoDead();
		dead.answer(reasoner);
		System.out.println(dead.deads);
		System.out.println("----------------- TEST 3 --------------------");
		reader = new VMReader();
		parseFile = reader.parseFile("FaMaTeS-inputs/case3/df-case3.vm");
		reasoner = new ChocoReasoner();
		parseFile.transformto(reasoner);
		dead= new ChocoDead();
		dead.answer(reasoner);
		System.out.println(dead.deads);
		System.out.println("----------------- TEST 4 --------------------");
		reader = new VMReader();
		parseFile = reader.parseFile("FaMaTeS-inputs/case4/df-case4.vm");
		reasoner = new ChocoReasoner();
		parseFile.transformto(reasoner);
		dead= new ChocoDead();
		dead.answer(reasoner);
		System.out.println(dead.deads);
		System.out.println("----------------- TEST 5 --------------------");
		reader = new VMReader();
		parseFile = reader.parseFile("FaMaTeS-inputs/case5/df-case5.vm");
		reasoner = new ChocoReasoner();
		parseFile.transformto(reasoner);
		dead= new ChocoDead();
		dead.answer(reasoner);
		System.out.println(dead.deads);
		System.out.println("----------------- TEST 6 --------------------");
		reader = new VMReader();
		parseFile = reader.parseFile("FaMaTeS-inputs/case6/df-case6.vm");
		reasoner = new ChocoReasoner();
		parseFile.transformto(reasoner);
		dead= new ChocoDead();
		dead.answer(reasoner);
		System.out.println(dead.deads);
		System.out.println("----------------- TEST 7 --------------------");
		reader = new VMReader();
		parseFile = reader.parseFile("FaMaTeS-inputs/case7/df-case7.vm");
		reasoner = new ChocoReasoner();
		parseFile.transformto(reasoner);
		dead= new ChocoDead();
		dead.answer(reasoner);
		System.out.println(dead.deads);
		System.out.println("----------------- TEST 8 --------------------");
		reader = new VMReader();
		parseFile = reader.parseFile("FaMaTeS-inputs/case8/df-case8.vm");
		reasoner = new ChocoReasoner();
		parseFile.transformto(reasoner);
		dead= new ChocoDead();
		dead.answer(reasoner);
		System.out.println(dead.deads);
	}

}
