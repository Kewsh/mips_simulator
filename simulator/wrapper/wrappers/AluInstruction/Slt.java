package simulator.wrapper.wrappers.AluInstruction;

import simulator.control.Simulator;
import simulator.gates.combinational.And;
import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class Slt extends Wrapper {

    public Slt(String label, String stream, Link... links){
        super(label, stream, links);
    }

    @Override
    public void initialize() {

        Sub subtractor1 = new Sub("subtractor1" , "64x32");
        And and1 = new And("and1");

        for (int i = 0; i < 64; i++)
            subtractor1.addInput(getInput(i));

        and1.addInput(subtractor1.getOutput(0), Simulator.trueLogic);

        for (int i = 0; i < 31; i++)
            addOutput(Simulator.falseLogic);
        addOutput(and1.getOutput(0));
    }
}
