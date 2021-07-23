package simulator.wrapper.wrappers.AluInstruction;

import simulator.gates.combinational.And;
import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class AluAnd extends Wrapper{

    public AluAnd(String label, String stream, Link... links){
        super(label, stream, links);
    }

    @Override
    public void initialize() {

        And[] ands = new And[32];

        for (int i = 0; i < 32; i++) {
            ands[i] = new And("AND" + (i + 1));
            ands[i].addInput(getInput(i), getInput(32 + i));
            addOutput(ands[i].getOutput(0));
        }
    }
}
