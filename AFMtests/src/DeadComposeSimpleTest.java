import java.util.ArrayList;
import java.util.Collection;

import fr.familiar.attributedfm.AttributedFeatureModel;
import fr.familiar.attributedfm.Constraint;
import fr.familiar.attributedfm.reasoning.ChocoReasoner;
import fr.familiar.attributedfm.reasoning.questions.ChocoValid;
import fr.familiar.readers.VMReader;


public class DeadComposeSimpleTest {

	public static void main(String[] args) throws Exception {
		String model1="./Composition/afm1.vm";
		String model2="./Composition/afm2.vm";
		Collection<String> modelsset= new ArrayList<String>();
		modelsset.add(model1);modelsset.add(model2);
		
		VMReader reader = new VMReader();
		AttributedFeatureModel afm1 = reader.parseFile(model1);
		
		ChocoReasoner reasoner = new ChocoReasoner();
		afm1.transformto(reasoner);
		ChocoValid validq= new ChocoValid();
		validq.answer(reasoner);
		System.out.println("The model afm1 is valid:"+validq.isValid());
		
		
		AttributedFeatureModel afm2 = reader.parseFile(model2);
		reasoner = new ChocoReasoner();
		afm2.transformto(reasoner);
		validq= new ChocoValid();
		validq.answer(reasoner);
		System.out.println("The model afm2 is valid:"+validq.isValid());
		
		
		Collection<Constraint> ctc = reader.parseConstratins(modelsset,"./Composition/ctcs.vm");
		afm1.compose(afm2, ctc);//now afm1 is the compose model
		
		reasoner = new ChocoReasoner();
		afm1.transformto(reasoner);
		validq= new ChocoValid();
		validq.answer(reasoner);
		System.out.println("The composed model is valid:"+validq.isValid());
	}

}
