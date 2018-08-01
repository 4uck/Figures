import java.util.ArrayList;
import java.util.List;

public class SolutionAlgorithm2 {

    private Coordinate point0;
    private Coordinate point1;

    private int dymax;
    private int dxmax;

    private int dxmin;
    private int dymin;

    private ConvexArea area;

    private int currentX;
    private int currentY;

    public SolutionAlgorithm2(int dymax, int dxmax, int dxmin, int dymin, ConvexArea area) {
        this.dymax = dymax;
        this.dxmax = dxmax;
        this.dxmin = dxmin;
        this.dymin = dymin;
        this.area = area;
    }

    public List<Step> algorithm_2(
            Coordinate point0,
            Coordinate point1
    ) throws AlgorithmNotFoundException {

        this.point0 = point0;
        this.point1 = point1;

        this.currentX = point0.getX();
        this.currentY = point0.getY();

        List<Step> steps = new ArrayList<>();

        int diffX = point1.getX() - point0.getX();
        int diffY = point1.getY() - point0.getY();

        if (diffY < 0) {
            steps.addAll(addList(diffY, dymax, Axis.Y));
            steps.addAll(addList(diffX, dxmax, Axis.X));
        }
        else if (diffX != 0){
            steps.addAll(addList(diffX, dxmax, Axis.X));

            if (diffY != 0)
                steps.addAll(addList(diffY, dymax, Axis.Y));
        }

        return steps;
    }

    private List<Step> addList(int diff, int max, Axis axis)
            throws AlgorithmNotFoundException {

        int excess = Math.abs(diff % max);
        int countSteps = Math.abs(diff / max);

        int directionX = diff < 0 ? -1 : 1;

        return distributeExcess(excess, directionX, countSteps, axis);
    }

    private List<Step> distributeExcessMod(int excess, int direction, Axis axis)
            throws AlgorithmNotFoundException {

        int max = axis.ordinal() == 0 ? dxmax : dymax;
        int min = axis.ordinal() == 0 ? dxmin : dymin;

        List<Step> steps = new ArrayList<>();

        if (excess >= min) {
            Step lastStep = new Step(axis, excess * direction);
            addSteps(lastStep, steps);
            return steps;
        }

        int modX = axis.ordinal() == 0 ? currentX + min * (-direction): currentX;
        int modY = axis.ordinal() == 1 ? currentY + min * (-direction): currentY;

        if (!area.isAccessPoint(new Coordinate(modX, modY))){

            modX = axis.ordinal() == 0 ? currentX + 2 * min * direction : currentX;
            modY = axis.ordinal() == 1 ? currentY + 2 * min * direction : currentY;

            if (!area.isAccessPoint(new Coordinate(modX, modY))) {
                throw new AlgorithmNotFoundException();
            }

            addSteps(new Step(axis, min * direction, 2), steps);
            direction = -direction;
            excess = min - excess;
        }else {
            addSteps(new Step(axis, min * (-direction)), steps);
        }

        for (int i = 0; i < excess; i++) {

            if (min + excess <= max){
                addSteps(new Step(axis, (min + excess) * direction), steps);
                return steps;
            }

            addSteps(new Step(axis, max * direction), steps);
            addSteps(new Step(axis, max * (-direction)), steps);

            excess = excess - (max - min);
        }

        return steps;
    }

    public List<Step> distributeExcess(int excess, int direction, int countSteps, Axis axis)
            throws AlgorithmNotFoundException {

        if (countSteps == 0)
            return distributeExcessMod(excess, direction, axis);

        int max = axis.ordinal() == 0 ? dxmax : dymax;
        int min = axis.ordinal() == 0 ? dxmin : dymin;

        List<Step> steps = new ArrayList<>();

        addSteps(new Step(axis, max * direction, countSteps), steps);

        if (excess == 0)
            return steps;

        List<Step> from = distributeExcessMod(excess, direction, axis);

        if (from != null)
            addAllSteps(from, steps);

        return steps;
    }

    private void addSteps(Step step, List<Step> steps){

        currentX = step.getAxis().ordinal() == 0 ?
                currentX + step.getLength() * step.getCountSteps(): currentX;
        currentY = step.getAxis().ordinal() == 1 ?
                currentY + step.getLength() * step.getCountSteps(): currentY;

        steps.add(step);
    }

    private void addAllSteps(List<Step> from, List<Step> to){

        for (Step step: from) {
            currentX = step.getAxis().ordinal() == 0 ?
                    currentX + step.getLength() * step.getCountSteps(): currentX;
            currentY = step.getAxis().ordinal() == 1 ?
                    currentY + step.getLength() * step.getCountSteps(): currentY;
        }

        to.addAll(from);
    }
}
