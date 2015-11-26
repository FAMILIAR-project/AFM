import fr.familiar.attributedfm.AttributedFeatureModel;
import fr.familiar.attributedfm.reasoning.ChocoReasoner;
import fr.familiar.attributedfm.reasoning.questions.ChocoProducts;
import fr.familiar.attributedfm.reasoning.questions.ChocoValid;
import fr.familiar.readers.VMReader;

public class ConfEnumeration {

	public static void main(String[] args) throws Exception {

		VMReader reader = new VMReader();
		reader.fakeRealSupport=true;
		AttributedFeatureModel parseFile = reader.parseFile("ISSTA2014.vm");
		
		ChocoReasoner reasoner = new ChocoReasoner();
		parseFile.transformto(reasoner);
		ChocoProducts validq= new ChocoProducts();
		validq.answer(reasoner);
	
	}

}
