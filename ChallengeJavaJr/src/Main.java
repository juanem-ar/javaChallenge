import java.lang.reflect.Array;
import java.util.*;

class Helper{

    /*  Enunciados:
     *   1- Multiplicar 2 numeros, sin utilizar el operador de multiplicacion.
     */
    public int multiplication(int a, int b){
        int result = a;
        for (int i = 0; i < b-1; i++) {
            result+=a;
        }
        return result;
    }

    /*
     *   2- Obtener el numero mayor dentro de un arreglo. Solo iterar 1 vez.
     */
    public int findHighest(int[] list){
        int result= list[0];

        for (int i = 1; i <= list.length-1; i++) {
            if (list[i]>=result){
                result = list[i];
            }
        }

        return result;
    }

    /*
     *   3- Dado un arreglo, eliminar todos los "undefined", "null", "false" y 0 (no sus valores en string). Iterar solo 1 vez.
     */
    public static List<Object> filterList(Object[] list){
        List<Object> validValues = new ArrayList();
        for (Object object : list) {
            if(object!=null) {
                if (object instanceof String || !(object instanceof Integer && object.equals(0) || object instanceof Boolean && object.equals(false))) {
                    validValues.add(object);
                }
            }
        }
        return validValues;
    }

    /*
     *   4- Dado un arreglo multidimensional, obtener todos los valores en un nuevo arreglo de una sola dimension.
     *      Usar recursividad Ejemplo: [1, [2, [3, 4]], 'hola', [1, 'buenos dias']] => [1, 2, 3, 4, 'hola', 1, 'buenos dias']
     */
    public List<Object> filterMultiList(List<Object> list){
        List<Object> result = new ArrayList();

        for (Object object: list) {
            if (object instanceof List){
                result.addAll(filterMultiList((List<Object>) object));
            }else{
                result.add(object);
            }
        }
        return result;
    }

    /*
     *   5- Dado un string, devolver un objeto/array que indique la palabra que mas veces se repite, y su cantidad.
     *      Ejemplo: "Este es un string, el cual es un string donde se repite muchas veces la palabra es" => {es: 3} / ['es' => 3]
     */
    public String countWords(String string){
        Map<String, Integer> wordCount = new HashMap<>();

        //divido en palabras el string separandolas por los espacios
        String[] words = string.split(" ");

        //se cuenta la cantidad de veces que se encuentra la palabra
        for (String word : words) {
            if (!wordCount.containsKey(word)) {
                wordCount.put(word, 1);
            } else {
                int count = wordCount.get(word);
                wordCount.put(word, count + 1);
            }
        }

        //filtro por la palabra mas encontrada
        String mostFrequentWord = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            if (count > maxCount) {
                mostFrequentWord = word;
                maxCount = count;
            }
        }

        return "{"+ mostFrequentWord + " => " + maxCount + "}";
    }

    /*
     *   6- Verificar si un string es un palíndromo.
     */
    public boolean isPalindrome(String str) {
        // Elimino los espacos en blanco y convierto en minuscula el string
        str = str.replaceAll("\\s+", "").toLowerCase();

        // Con el builder invierto el string
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        String strInvertido = sb.toString();

        return str.equals(strInvertido);
    }

    /*
     *   7- Dado 3 numeros, devolver el mayor. Adaptar esto para que funcione con cualquier cantidad de numeros.
     */
    public static int getMax(int... numbers) {
        int max = Integer.MIN_VALUE;
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}

public class Main {
    private static Object undefined;

    public static void main(String[] args) {
        Helper helper = new Helper();

        /*  Resoluciones de enunciados:
        *   1
        */
        int n1 = helper.multiplication(13,9);
        System.out.println("1 - Multiplicando 2 num: " + n1);

        /*
         *   2
         */
        int[] numbers = {18,25,32,40,55,69,729,98,93};
        int n2 = helper.findHighest(numbers);
        System.out.println("2 - Obteniendo num mayor pasando arreglo por parametro: " + n2);


        /*
         *   3
         */
        Object[] startList = {1, undefined  , null, false, 0, "Juan López", "ramiro",4,84,true};
        List<Object> validList = helper.filterList(startList);
        System.out.println("3 - Filtrando lista y eliminando los datos : " + validList);

        /*
         *   4
         */
        List<Object> original = Arrays.asList(1,Arrays.asList(2,Arrays.asList(3, 4)), "hola",Arrays.asList(1, "buenos dias"));
        List<Object> filterList = helper.filterMultiList(original);
        System.out.println("4 - Obteniendo datos en un arreglo unidimensional : " + filterList);

        /*
         *   5
         */
        String response = helper.countWords("Este es un string, el cual es un string donde se repite muchas veces la palabra es");
        System.out.println("5 - Obteniendo la palabra que mas se repite y la cantidad de veces que lo hace : " +response);

        /*
         *   6
         */
        System.out.println("6 - la palabra 'reconocer' es palíndromo?: " + helper.isPalindrome("reconocer"));
        System.out.println("6 - la palabra 'marido' es palíndromo?: " + helper.isPalindrome("marido"));

        /*
         *   7
         */
        int max = helper.getMax(1,2,3,4,5,6,7,8,9,1523);
        System.out.println("7 -Dados los números: 1,2,3,4,5,6,7,8,9,123, el numero más grande es: " + max);
    }
}