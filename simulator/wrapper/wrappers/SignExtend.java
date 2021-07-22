package simulator.wrapper.wrappers;

import simulator.control.Simulator;
import simulator.gates.combinational.And;
import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class SignExtend extends Wrapper {
    public SignExtend(String label, String stream, Link... links) {
        super(label, stream, links);
    }

    @Override
    public void initialize() {

        And and1 = new And("and1") ;
        and1.addInput(getInput(0) , Simulator.trueLogic);

        for (int i = 0 ; i < 16; i++)
            addOutput(and1.getOutput(0));

        for (int i = 0; i < 16; i++)
            addOutput(getInput(i));
    }
}
