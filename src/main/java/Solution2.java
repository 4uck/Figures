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

        List<Step> steps = new ArrayList<>();

        int diffX = point1.getX() - point0.getX();
        int diffY = point1.getY() - point0.getY();

        if (diffX != 0) {
            int excessX = Math.abs(diffX % dxmax);
            int countStepsX = Math.abs(diffX / dxmax);

            int directionX = diffX < 0 ? -1 : 1;

            steps.addAll(distributeExcess(excessX, directionX, countStepsX, Axis.X));
        }

        if (diffY != 0) {
            int excessY = Math.abs(diffY % dymax);
            int countStepsY = Math.abs(diffY / dymax);

            int directionY = diffY < 0 ? -1 : 1;

            if (diffY < 0)
                steps.addAll(0, distributeExcess(excessY, directionY, countStepsY, Axis.Y));
            else
                steps.addAll(distributeExcess(excessY, directionY, countStepsY, Axis.Y));
        }

        return steps;
    }

    private List<Step> distributeExcessMod(int excess, int direction, Axis axis) {

        int max = axis.ordinal() == 0 ? dxmax : dymax;
        int min = axis.ordinal() == 0 ? dxmin : dymin;

        List<Step> steps = new ArrayList<>();

        if (excess >= min) {
            Step lastStep = new Step(axis, excess * direction);
            steps.add(lastStep);
            return steps;
        }

        int stepMaxCount = excess / (max - min);
        int stepLastLength = excess % (max - min);

        int x = 0;
        int y = 0;

        if (stepMaxCount  == 0 && stepLastLength + min < max){
            x = axis.ordinal() == 0 ?
                    (excess + min) * (-direction) + point1.getX() : point1.getX();

            y = axis.ordinal() == 1 ?
                    (excess + min) * (-direction) + point1.getY() : point1.getY();
        }else {
            x = axis.ordinal() == 0 ?
                    (stepLastLength + (max * stepMaxCount)) * (-direction) + point1.getX() : point1.getX();

            y = axis.ordinal() == 1 ?
                    (stepLastLength + (max * stepMaxCount)) * (-direction) + point1.getY() : point1.getY();
        }

        Coordinate myPoint = new Coordinate(x, y);

        if (myPoint.getY() >= area.getVertex3().getY() &&
            myPoint.getX() >= area.getVertex3().getX() &&
            area.isAccessPoint(myPoint)) {

            if (stepMaxCount > 0) {
                steps.add(new Step(axis, min * (-direction), stepMaxCount));
                steps.add(new Step(axis, max * direction, stepMaxCount));
            }

            if (stepLastLength > 0)
                steps.add(new Step(axis, stepLastLength * (-direction)));

            return steps;

        }

        steps.add(new Step(axis, min * direction));

        excess = Math.abs(excess - min);
        direction = -direction;

        stepMaxCount = excess / (max - min);
        stepLastLength = excess % (max - min);

        if (stepMaxCount  == 0 && stepLastLength + min < max){
            x = axis.ordinal() == 0 ?
                    point1.getX() + excess + min : point1.getX();

            y = axis.ordinal() == 1 ?
                    point1.getY() + excess + min : point1.getY();
        }else {
            x = axis.ordinal() == 0 ?
                    (stepLastLength + (max * stepMaxCount)) * (-direction) + point1.getX() : point1.getX();

            y = axis.ordinal() == 1 ?
                    (stepLastLength + (max * stepMaxCount)) * (-direction) + point1.getY() : point1.getY();
        }

//        x = axis.ordinal() == 0 ? excess + point1.getX() + (min * direction) : point1.getX();
//        y = axis.ordinal() == 1 ? excess + point1.getY() + (min * direction) : point1.getY();

        myPoint = new Coordinate(x, y);

        if (myPoint.getY() >= area.getVertex3().getY() && area.isAccessPoint(myPoint)) {

            if (stepMaxCount > 0) {
                steps.add(new Step(axis, min * (-direction), stepMaxCount));
                steps.add(new Step(axis, max * direction, stepMaxCount));
            }

            if (stepLastLength > 0) {
                steps.add(new Step(axis, min * (-direction)));
                steps.add(new Step(axis, (min + stepLastLength) * (direction)));
            }

            return steps;

        }


//        Step minStep = new Step(axis, min);
//
////        int currentAxis = axis.ordinal() == 0 ? point1.getX() : point1.getY();
//
//        while (true){
//
//            int x = axis.ordinal() == 0 ? direction *(min + excess) + point1.getX() : point1.getX();
//            int y = axis.ordinal() == 1 ? direction *(min + excess) + point1.getY() : point1.getY();
//
//            Coordinate myPoint = new Coordinate(x, y);
//
//            if (myPoint.getY() > area.getVertex3().getY() && area.isAccessPoint(myPoint)){
//                if (excess)
//            }
//        }

        return steps;
    }

    public List<Step> distributeExcess(int excess, int direction, int countSteps, Axis axis) {

        if (countSteps == 0)
            return distributeExcessMod(excess, direction, axis);

        int max = axis.ordinal() == 0 ? dxmax : dymax;
        int min = axis.ordinal() == 0 ? dxmin : dymin;

        List<Step> steps = new ArrayList<>();

        Step stepMax = new Step(axis, max * direction, countSteps);
        Step stepMin = new Step(axis, min * direction, 0);

        for (int i = 0; i < countSteps; i++) {

            if (excess < max - min) {

                Step lastStep = new Step(axis, excess * direction);
                steps.add(lastStep);
                excess = 0;

            } else if (excess == max - min) {
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
                    steps.add(stepMax);

                if (stepMin.getCountSteps() > 0)
                    steps.add(stepMin);


                break;
            }
        }

        return steps;
    }
}
