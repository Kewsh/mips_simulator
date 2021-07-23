package simulator.wrapper.wrappers.AluInstruction;

import simulator.gates.combinational.Or;
import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class AluOr extends Wrapper{

    public AluOr(String label, String stream, Link... links){
        super(label, stream, links);
    }

    @Override
    public void initialize() {

        Or[] ors = new Or[32];

        for (int i = 0; i < 32; i++){
            ors[i] = new Or("OR" + (i+1));
            ors[i].addInput(getInput(i), getInput(32+i));
            addOutput(ors[i].getOutput(0));
        }
    }
}
