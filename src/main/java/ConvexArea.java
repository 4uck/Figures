
public class ConvexArea {

    private Coordinate vertex0;
    private Coordinate vertex1;
    private Coordinate vertex3;

    private int tg;

    public ConvexArea(Coordinate vertex0, Coordinate vertex1) {
        this.vertex0 = vertex0;
        this.vertex1 = vertex1;
        this.vertex3 = new Coordinate(vertex0.getX(), vertex1.getY());
        setTg();
    }

    public Coordinate getVertex0() {
        return vertex0;
    }

    public Coordinate getVertex1() {
        return vertex1;
    }

    public Coordinate getVertex3() {
        return vertex3;
    }

    private void setTg(){
        int a = vertex0.getY() - vertex3.getY();
        int b = vertex1.getX() - vertex3.getX();

        this.tg = b/a;
    }

    public boolean isAccessPoint(Coordinate point){

        int a_mod = vertex0.getY() - point.getY();
        int b_mod = tg * a_mod;

        if (vertex0.getX() + b_mod <= point.getX())
            return false;

        return true;
    }


}
