package simulator.wrapper.wrappers;

import simulator.gates.combinational.And;
import simulator.gates.combinational.Not;
import simulator.gates.combinational.Or;
import simulator.network.Link;
import simulator.wrapper.Wrapper;


public class Mux2x1 extends Wrapper {

    public Mux2x1(String label, String stream, Link... links) {
        super(label, stream, links);
    }

    @Override
    public void initialize() {

        And a1 = new And("And1") ;
        And a2 = new And("And2") ;

        Not not = new Not("not") ;
        Or or = new Or("Or") ;

        not.addInput(getInput(2));
        a1.addInput(getInput(0), not.getOutput(0));
        a2.addInput(getInput(1), getInput(2));
        or.addInput(a1.getOutput(0), a2.getOutput(0));

        addOutput(or.getOutput(0));
    }
}
