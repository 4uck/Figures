import java.util.ArrayList;
import java.util.List;

public class Solution2 {

    private Coordinate point0;
    private Coordinate point1;

    private int dymax;
    private int dxmax;

    private int dxmin;
    private int dymin;

    private ConvexArea area;

    private int currentX;
    private int currentY;

    public Solution2(int dymax, int dxmax, int dxmin, int dymin, ConvexArea area) {
        this.dymax = dymax;
        this.dxmax = dxmax;
        this.dxmin = dxmin;
        this.dymin = dymin;
        this.area = area;
    }

    public List<Step> algorithm_2(
            Coordinate point0,
            Coordinate point1
    ) {

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

    private List<Step> addList(int diff, int max, Axis axis){

        int excess = Math.abs(diff % max);
        int countSteps = Math.abs(diff / max);

        int directionX = diff < 0 ? -1 : 1;

        return distributeExcess(excess, directionX, countSteps, axis);
    }

    private List<Step> distributeExcessMod(int excess, int direction, Axis axis) {

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
                System.out.println("ALGORITHM NOT FOUND");
                return null;
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

//    private List<Step> distributeExcessMod(int excess, int direction, Axis axis) {
//
//        int max = axis.ordinal() == 0 ? dxmax : dymax;
//        int min = axis.ordinal() == 0 ? dxmin : dymin;
//
//        List<Step> steps = new ArrayList<>();
//
//        if (excess >= min) {
//            Step lastStep = new Step(axis, excess * direction);
//            steps.add(lastStep);
//            return steps;
//        }
//
//        int stepMaxCount = excess / (max - min);
//        int stepLastLength = excess % (max - min);
//
//        int x = 0;
//        int y = 0;
//
//        if (stepMaxCount  == 0 && stepLastLength + min < max){
//            x = axis.ordinal() == 0 ?
//                    (excess + min) * (-direction) + point1.getX() : point1.getX();
//
//            y = axis.ordinal() == 1 ?
//                    (excess + min) * (-direction) + point1.getY() : point1.getY();
//        }else {
//            x = axis.ordinal() == 0 ?
//                    (stepLastLength + (max * stepMaxCount)) * (-direction) + point1.getX() : point1.getX();
//
//            y = axis.ordinal() == 1 ?
//                    (stepLastLength + (max * stepMaxCount)) * (-direction) + point1.getY() : point1.getY();
//        }
//
//        Coordinate myPoint = new Coordinate(x, y);
//
//        if (myPoint.getY() >= area.getVertex3().getY() &&
//            myPoint.getX() >= area.getVertex3().getX() &&
//            area.isAccessPoint(myPoint)) {
//
//            if (stepMaxCount > 0) {
//                steps.add(new Step(axis, min * (-direction), stepMaxCount));
//                steps.add(new Step(axis, max * direction, stepMaxCount));
//            }
//
//            if (stepLastLength > 0)
//                steps.add(new Step(axis, stepLastLength * (-direction)));
//
//            return steps;
//
//        }
//
//        steps.add(new Step(axis, min * direction));
//
//        excess = Math.abs(excess - min);
//        direction = -direction;
//
//        stepMaxCount = excess / (max - min);
//        stepLastLength = excess % (max - min);
//
//        if (stepMaxCount  == 0 && stepLastLength + min < max){
//            x = axis.ordinal() == 0 ?
//                    point1.getX() + excess + min : point1.getX();
//
//            y = axis.ordinal() == 1 ?
//                    point1.getY() + excess + min : point1.getY();
//        }else {
//            x = axis.ordinal() == 0 ?
//                    (stepLastLength + (max * stepMaxCount)) * (-direction) + point1.getX() : point1.getX();
//
//            y = axis.ordinal() == 1 ?
//                    (stepLastLength + (max * stepMaxCount)) * (-direction) + point1.getY() : point1.getY();
//        }
//
//        myPoint = new Coordinate(x, y);
//
//        if (myPoint.getY() >= area.getVertex3().getY() && area.isAccessPoint(myPoint)) {
//
//            if (stepMaxCount > 0) {
//                steps.add(new Step(axis, min * (-direction), stepMaxCount));
//                steps.add(new Step(axis, max * direction, stepMaxCount));
//            }
//
//            if (stepLastLength > 0) {
//                steps.add(new Step(axis, min * (-direction)));
//                steps.add(new Step(axis, (min + stepLastLength) * (direction)));
//            }
//
//            return steps;
//
//        }
//
//        return steps;
//    }

    public List<Step> distributeExcess(int excess, int direction, int countSteps, Axis axis) {

        if (countSteps == 0)
            return distributeExcessMod(excess, direction, axis);

        int max = axis.ordinal() == 0 ? dxmax : dymax;
        int min = axis.ordinal() == 0 ? dxmin : dymin;

        List<Step> steps = new ArrayList<>();

        Step stepMax = new Step(axis, max * direction, countSteps);
        Step stepMin = new Step(axis, min * direction, 0);

        for (int i = 0; i < countSteps; i++) {

            if (excess > min) {

                Step lastStep = new Step(axis, excess * direction);
                addSteps(lastStep, steps);
                excess = 0;

            } else if (excess == min) {
                stepMax.setCountSteps(stepMax.getCountSteps() - 1);
                stepMin.setCountSteps(stepMin.getCountSteps() + 2);
                excess = 0;
            } else {
                excess = excess - (max - min);

                stepMax.setCountSteps(stepMax.getCountSteps() - 1);
                stepMin.setCountSteps(stepMin.getCountSteps() + 1);
            }

            if (excess == 0) {

                if (stepMax.getCountSteps() > 0)
                    addSteps(stepMax, steps);

                if (stepMin.getCountSteps() > 0)
                    addSteps(stepMin, steps);

                break;
            }
        }

        return steps;
    }

    private void addSteps(Step step, List<Step> steps){

        currentX = step.getAxis().ordinal() == 0 ? currentX + step.getLength() * step.getCountSteps(): currentX;
        currentY = step.getAxis().ordinal() == 1 ? currentY + step.getLength() * step.getCountSteps(): currentY;

        steps.add(step);
    }
}
