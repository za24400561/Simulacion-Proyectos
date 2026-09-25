public class Main {

    static double[] advertising = {
        43.60, 50.44, 59.01, 66.30, 82.36,
        92.15, 100.51, 110.06, 111.51
    };

    static double[] sales = {
        1261.08, 1475.28, 1657.52, 2059.05, 2303.76,
        2512.64, 2751.46, 2787.67, 2939.13
    };

    public static void main(String[] args) {

        int n = advertising.length;

        double sumaX = 0;
        double sumaY = 0;

        for (int i = 0; i < n; i++) {
            sumaX += advertising[i];
            sumaY += sales[i];
        }

        double promedioX = sumaX / n;
        double promedioY = sumaY / n;

        double numerador = 0;
        double denominador = 0;

        for (int i = 0; i < n; i++) {

            numerador +=
                (advertising[i] - promedioX) *
                (sales[i] - promedioY);

            denominador +=
                (advertising[i] - promedioX) *
                (advertising[i] - promedioX);
        }

        double beta1 = numerador / denominador;

        double beta0 = promedioY - (beta1 * promedioX);

        System.out.println("SIMPLE LINEAR REGRESSION");
        System.out.println("========================");

        System.out.printf("Beta 0 = %.4f%n", beta0);
        System.out.printf("Beta 1 = %.4f%n", beta1);

        System.out.println();
        System.out.println("Ecuacion de regresion:");

        System.out.printf(
            "y = %.4f + %.4fx%n",
            beta0,
            beta1
        );

        double[] valores = {40, 50, 60, 70, 80};

        System.out.println();
        System.out.println("PREDICCIONES");
        System.out.println("============");

        for (int i = 0; i < 5; i++) {

            double x = valores[i];

            double y = beta0 + (beta1 * x);

            System.out.printf(
                "Advertising = %.2f -> Sales = %.2f%n",
                x,
                y
            );
        }
    }
}
