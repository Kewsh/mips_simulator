package simulator.wrapper.wrappers;

import simulator.wrapper.wrappers.AluInstruction.*;
import simulator.control.Simulator;
import simulator.gates.combinational.Or;
import simulator.network.Link;
import simulator.wrapper.Wrapper;

public class Alu extends Wrapper {
    public Alu(String label, String stream, Link... links) {
        super(label, stream, links);
    }

    @Override
    public void initialize() {


        Add adder = new Add("ADDER", "64X32",
                getInput(31), getInput(30), getInput(29), getInput(28), getInput(27), getInput(26), getInput(25),
                getInput(24), getInput(23), getInput(22), getInput(21), getInput(20), getInput(19), getInput(18),
                getInput(17), getInput(16), getInput(15), getInput(14), getInput(13), getInput(12), getInput(11),
                getInput(10), getInput(9), getInput(8), getInput(7), getInput(6), getInput(5), getInput(4), getInput(3),
                getInput(2), getInput(1), getInput(0), getInput(63), getInput(62), getInput(61), getInput(60), getInput(59),
                getInput(58), getInput(57), getInput(56), getInput(55), getInput(54), getInput(53), getInput(52), getInput(51),
                getInput(50), getInput(49), getInput(48), getInput(47), getInput(46), getInput(45), getInput(44), getInput(43),
                getInput(42), getInput(41), getInput(40), getInput(39), getInput(38), getInput(37), getInput(36), getInput(35),
                getInput(34), getInput(33), getInput(32));


        AluOr or = new AluOr("OR1","64X32",
                getInput(0), getInput(1), getInput(2), getInput(3), getInput(4), getInput(5), getInput(6),
                getInput(7), getInput(8), getInput(9), getInput(10), getInput(11), getInput(12), getInput(13),
                getInput(14), getInput(15), getInput(16), getInput(17), getInput(18), getInput(19), getInput(20),
                getInput(21), getInput(22), getInput(23), getInput(24), getInput(25), getInput(26), getInput(27), getInput(28),
                getInput(29), getInput(30), getInput(31), getInput(32), getInput(33), getInput(34), getInput(35), getInput(36),
                getInput(37), getInput(38), getInput(39), getInput(40), getInput(41), getInput(42), getInput(43), getInput(44),
                getInput(45), getInput(46), getInput(47), getInput(48), getInput(49), getInput(50), getInput(51), getInput(52),
                getInput(53), getInput(54), getInput(55), getInput(56), getInput(57), getInput(58), getInput(59), getInput(60),
                getInput(61), getInput(62), getInput(63));


        AluAnd and = new AluAnd("AND","64x32",
                getInput(0), getInput(1), getInput(2), getInput(3), getInput(4), getInput(5), getInput(6),
                getInput(7), getInput(8), getInput(9), getInput(10), getInput(11), getInput(12), getInput(13),
                getInput(14), getInput(15), getInput(16), getInput(17), getInput(18), getInput(19), getInput(20),
                getInput(21), getInput(22), getInput(23), getInput(24), getInput(25), getInput(26), getInput(27), getInput(28),
                getInput(29), getInput(30), getInput(31), getInput(32), getInput(33), getInput(34), getInput(35), getInput(36),
                getInput(37), getInput(38), getInput(39), getInput(40), getInput(41), getInput(42), getInput(43), getInput(44),
                getInput(45), getInput(46), getInput(47), getInput(48), getInput(49), getInput(50), getInput(51), getInput(52),
                getInput(53), getInput(54), getInput(55), getInput(56), getInput(57), getInput(58), getInput(59), getInput(60),
                getInput(61), getInput(62), getInput(63));


        Sub sub = new Sub("SUB","64X32",
                getInput(31), getInput(30), getInput(29), getInput(28), getInput(27), getInput(26), getInput(25),
                getInput(24), getInput(23), getInput(22), getInput(21), getInput(20), getInput(19), getInput(18),
                getInput(17), getInput(16), getInput(15), getInput(14), getInput(13), getInput(12), getInput(11),
                getInput(10), getInput(9), getInput(8), getInput(7), getInput(6), getInput(5), getInput(4), getInput(3),
                getInput(2), getInput(1), getInput(0), getInput(63), getInput(62), getInput(61), getInput(60), getInput(59),
                getInput(58), getInput(57), getInput(56), getInput(55), getInput(54), getInput(53), getInput(52), getInput(51),
                getInput(50), getInput(49), getInput(48), getInput(47), getInput(46), getInput(45), getInput(44), getInput(43),
                getInput(42), getInput(41), getInput(40), getInput(39), getInput(38), getInput(37), getInput(36), getInput(35),
                getInput(34), getInput(33), getInput(32));


        Slt slt = new Slt("SLT" ,"64X32",
                getInput(31), getInput(30), getInput(29), getInput(28), getInput(27), getInput(26), getInput(25),
                getInput(24), getInput(23), getInput(22), getInput(21), getInput(20), getInput(19), getInput(18),
                getInput(17), getInput(16), getInput(15), getInput(14), getInput(13), getInput(12), getInput(11),
                getInput(10), getInput(9), getInput(8), getInput(7), getInput(6), getInput(5), getInput(4), getInput(3),
                getInput(2), getInput(1), getInput(0), getInput(63), getInput(62), getInput(61), getInput(60), getInput(59),
                getInput(58), getInput(57), getInput(56), getInput(55), getInput(54), getInput(53), getInput(52), getInput(51),
                getInput(50), getInput(49), getInput(48), getInput(47), getInput(46), getInput(45), getInput(44), getInput(43),
                getInput(42), getInput(41), getInput(40), getInput(39), getInput(38), getInput(37), getInput(36), getInput(35),
                getInput(34), getInput(33), getInput(32));


        Multiplexer8x1[] muxArr = new Multiplexer8x1[32];

        for (int i = 0; i < 32; i++) {
            muxArr[i] = new Multiplexer8x1("MUX" + i, "11x1");
            muxArr[i].addInput(and.getOutput(i), or.getOutput(i), adder.getOutput(i), Simulator.trueLogic, Simulator.trueLogic,
                    Simulator.trueLogic, sub.getOutput(i), slt.getOutput(i), getInput(64), getInput(65), getInput(66));
            addOutput(muxArr[i].getOutput(0));
        }

        Or orsub = new Or("Orsub",sub.getOutput(0),sub.getOutput(1),
                sub.getOutput(2),sub.getOutput(3),sub.getOutput(4),sub.getOutput(5),sub.getOutput(6),
                sub.getOutput(7),sub.getOutput(8),sub.getOutput(9),sub.getOutput(10),sub.getOutput(11),sub.getOutput(12),
                sub.getOutput(13),sub.getOutput(14),sub.getOutput(15),sub.getOutput(16),sub.getOutput(17),sub.getOutput(18),
                sub.getOutput(19),sub.getOutput(20),sub.getOutput(21),sub.getOutput(22),sub.getOutput(23),sub.getOutput(24),
                sub.getOutput(25),sub.getOutput(26),sub.getOutput(27),sub.getOutput(28),sub.getOutput(29),sub.getOutput(30),
                sub.getOutput(31));

        Mux2x1 mux2x1 = new Mux2x1("Mux2x1" , "3X1");
        mux2x1.addInput(Simulator.trueLogic);
        mux2x1.addInput(Simulator.falseLogic);
        mux2x1.addInput(orsub.getOutput(0));

        addOutput(mux2x1.getOutput(0)); //zero
    }
}
