package src.java.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int quantidadeTemperaturas;
        String temperaturaDe;
        String temperaturaPara;
        String fecharPrograma;
        boolean fimPrograma = false;
        double temperatura;
        double mediaDe = 0;
        double mediaPara = 0;
        Scanner in;
        ArrayList<Double> celsius = new ArrayList<>();
        ArrayList<Double> fahrenheit  = new ArrayList<>();
        ArrayList<Double> kelvin  = new ArrayList<>();
        ArrayList<Double> temperaturaTransformada = new ArrayList<>();


        while (!fimPrograma){
            System.out.print("Informe a quantidade de temperaturas a serem transformadas: ");
            in = new Scanner(System.in);
            try{
                quantidadeTemperaturas = in.nextInt();
            }catch (InputMismatchException e){
                System.out.printf("Informe apenas números inteiros!");
                continue;
            }


            System.out.println("Temperaturas disponíveis: \nCelsius (C)\nFahrenheit (F) \nKelvin (K)");
            while (true){
                System.out.println("Informe apenas a primeira letra da temperatura.");
                System.out.print("De qual temperatura deseja converter: ");
                in = new Scanner(System.in);
                temperaturaDe = in.next().toUpperCase();
                if (temperaturaDe.equals("C") || temperaturaDe.equals("F") || temperaturaDe.equals("K")) break;
            }

            while (true){
                System.out.println("\nInforme apenas a primeira letra da temperatura.");
                System.out.print("Para qual temperatura deseja que seja convertido: ");
                in = new Scanner(System.in);
                temperaturaPara = in.next().toUpperCase();
                if (temperaturaPara.equals(temperaturaDe))
                    System.out.println("\nInforme uma temperatura diferente da primeira.");
                else
                    if (temperaturaPara.equals("C") || temperaturaPara.equals("F") || temperaturaPara.equals("K")) break;
            }


            for (int i = 0; i < quantidadeTemperaturas; i++) {
                System.out.print("Informe uma temperatura: ");
                in = new Scanner(System.in).useLocale(Locale.US);;
                try{
                   temperatura = in.nextDouble();
                }catch (InputMismatchException e){
                    System.out.printf("\nInforme apenas números racionais. Use o ponto no lugar da vírgula, se necessário.");
                    i--;
                    continue;
                }


                if(temperaturaDe.equals("C")){
                    celsius.add(temperatura);
                    temperaturaTransformada.add(transformandoCelsius(temperatura, temperaturaPara));
                    System.out.printf("%.3fº%s equivale a %.3fº%s\n", temperatura, temperaturaDe, temperaturaTransformada.get(i), temperaturaPara);
                } else if (temperaturaDe.equals("F")) {
                    fahrenheit.add(temperatura);
                    temperaturaTransformada.add(transformandoFahrenheit(temperatura, temperaturaPara));
                    System.out.printf("%.3fº%s equivale a %.3fº%s\n", temperatura, temperaturaDe, temperaturaTransformada.get(i), temperaturaPara);
                }else {
                    kelvin.add(temperatura);
                    temperaturaTransformada.add(transformandoKelvin(temperatura, temperaturaPara));
                    System.out.printf("%.3fº%s equivale a %.3fº%s\n", temperatura, temperaturaDe, temperaturaTransformada.get(i), temperaturaPara);
                }
            }


            if(temperaturaDe.equals("C")){
                for (int i = 0; i < quantidadeTemperaturas; i++) {
                   mediaDe += celsius.get(i);
                   mediaPara += temperaturaTransformada.get(i);
                }
            } else if (temperaturaDe.equals("F")) {
                for (int i = 0; i < quantidadeTemperaturas; i++) {
                    mediaDe += fahrenheit.get(i);
                    mediaPara += temperaturaTransformada.get(i);
                }
            }else {
                for (int i = 0; i < quantidadeTemperaturas; i++) {
                    mediaDe += kelvin.get(i);
                    mediaPara += temperaturaTransformada.get(i);
                }
            }


            System.out.printf("\nA média das temperaturas de entrada foi %.3f", mediaDe/quantidadeTemperaturas);
            System.out.printf("\nA média das temperaturas transformadas foi %.3f", mediaPara/quantidadeTemperaturas);


            System.out.print("\nDeseja continuar convertendo temperaturas? Digite 'S' para sim e qualquer outra coisa para para não: ");
            in = new Scanner(System.in);
            fecharPrograma = in.next().toUpperCase();
            if(!fecharPrograma.equals("S")) fimPrograma = true;

            mediaDe = 0;
            mediaPara = 0;
            celsius.clear();
            kelvin.clear();
            fahrenheit.clear();
            temperaturaTransformada.clear();
        }
    }

    private static double transformandoFahrenheit (double fahrenheit, String temperaturaPara){
        if(temperaturaPara.equals("C")){
            double celsius = (fahrenheit - 32) / (1.8);
            return celsius;
        }else {
            double kelvin = ((fahrenheit-32) * (5.0/9.0)) + 273.15;
            return kelvin;
        }
    }

    private static double transformandoCelsius (double celsius, String temperaturaPara){
        if(temperaturaPara.equals("F")){
            double fahrenheit = (celsius * 1.8) + 32;
            return fahrenheit;
        }else {
            double kelvin = celsius + 273.15;
            return kelvin;
        }
    }

    private static double transformandoKelvin (double kelvin, String temperaturaPara){
        if(temperaturaPara.equals("C")){
            double celsius = kelvin - 273.15;
            return celsius;
        }else {
            double fahrenheit = ((kelvin - 273.15) * 1.8) + 32;
            return fahrenheit;
        }
    }
}
