import fr.familiar.attributedfm.AttributedFeatureModel;
import fr.familiar.attributedfm.reasoning.ChocoReasoner;
import fr.familiar.attributedfm.reasoning.questions.ChocoValid;
import fr.familiar.readers.VMReader;


public class ValidSimpleTest {

	public static void main(String[] args) throws Exception {
		
		VMReader reader = new VMReader();
		AttributedFeatureModel parseFile = reader.parseFile("ISSTA2014.vm");
		
		ChocoReasoner reasoner = new ChocoReasoner();
		parseFile.transformto(reasoner);
		ChocoValid validq= new ChocoValid();
		validq.answer(reasoner);
		System.out.println("The model is valid:"+validq.isValid());
	}

}
