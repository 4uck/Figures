public class Step {

    private Axis axis;
    private int length;
    private int countSteps;


    public Step(Axis axis, int length) {
        this.axis = axis;
        this.length = length;
        this.countSteps = 1;
    }

    public Step(Axis axis, int length, int countSteps) {
        this.axis = axis;
        this.length = length;
        this.countSteps = countSteps;
    }

    public Axis getAxis() {
        return axis;
    }

    public void setAxis(Axis axis) {
        this.axis = axis;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCountSteps() {
        return countSteps;
    }

    public void setCountSteps(int countSteps) {
        this.countSteps = countSteps;
    }

    @Override
    public String toString() {
        return "Step{" +
                "axis=" + axis +
                ", length=" + length +
                ", countSteps=" + countSteps +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Step step = (Step) o;

        if (length != step.length) return false;
        if (countSteps != step.countSteps) return false;
        return axis == step.axis;
    }

    @Override
    public int hashCode() {
        int result = axis != null ? axis.hashCode() : 0;
        result = 31 * result + length;
        result = 31 * result + countSteps;
        return result;
    }
}
