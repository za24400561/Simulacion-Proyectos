public class RegresionLineal {

    private double[] sales;
    private double[] advertising;
    
    private double beta0;
    private double beta1;

    // Constructor que recibe datos externos y realiza validaciones
    public RegresionLineal(double[] advertising, double[] sales) {
        if (advertising == null || sales == null || advertising.length != sales.length || advertising.length < 2) {
            throw new IllegalArgumentException("Los arreglos deben ser válidos, de igual tamaño y con al menos 2 datos.");
        }
        
        this.advertising = advertising;
        this.sales = sales;
        
        // Se calculan los coeficientes al instanciar la clase
        calcularCoeficientes();
    }

    private void calcularCoeficientes() {
        double promedioX = 0;
        double promedioY = 0;
        int n = advertising.length;

        for (int i = 0; i < n; i++) {
            promedioX += advertising[i];
            promedioY += sales[i];
        }

        promedioX /= n;
        promedioY /= n;

        double numerador = 0;
        double denominador = 0;

        for (int i = 0; i < n; i++) {
            double diffX = advertising[i] - promedioX;
            numerador += diffX * (sales[i] - promedioY);
            denominador += diffX * diffX;
        }

        if (denominador == 0) {
            throw new ArithmeticException("La varianza de X es cero; no se puede calcular la regresión.");
        }

        this.beta1 = numerador / denominador;
        this.beta0 = promedioY - (this.beta1 * promedioX);
    }

    public double getBeta0() {
        return beta0;
    }

    public double getBeta1() {
        return beta1;
    }

    public double predecir(double x) {
        return beta0 + (beta1 * x);
    }

    public void mostrarEcuacion() {
        System.out.println("\nECUACION DE REGRESION");
        System.out.println("----------------------");
        System.out.printf("Beta 0 = %.4f%n", beta0);
        System.out.printf("Beta 1 = %.4f%n", beta1);
        System.out.printf("y = %.4f + %.4fx%n", beta0, beta1);
    }

    public static void main(String[] args) {
        double[] sales = {
            1261.08, 1475.28, 1657.52, 2059.05, 2303.76, 2512.64, 2751.46, 2787.67, 2939.13
        };

        double[] advertising = {
            43.60, 50.44, 59.01, 66.30, 82.36, 92.15, 100.51, 110.06, 111.51
        };

        RegresionLineal modelo = new RegresionLineal(advertising, sales);
        modelo.mostrarEcuacion();

        double valorPrueba = 120.0;
        System.out.printf("%nPredicción de ventas para publicidad de %.2f: %.2f%n", 
                          valorPrueba, modelo.predecir(valorPrueba));
    }
}
