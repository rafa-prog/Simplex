import java.util.List;

public class Troca {
    private int col1;
    private int col2;
    private List<Double> listaNegra;

    public Troca(int col1, int col2, List<Double> listaNegra) {
        this.col1 = col1;
        this.col2 = col2;
        this.listaNegra = listaNegra;
    }

    public int getCol1() {
        return col1;
    }

    public int getCol2() {
        return col2;
    }

    public List<Double> getListaNegra() {
        return listaNegra;
    }

    public void banir(double num) {
        listaNegra.add(num);
    }
}
