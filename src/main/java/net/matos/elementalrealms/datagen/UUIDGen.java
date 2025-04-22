package net.matos.elementalrealms.datagen;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

class UUIDGen {
    UUIDGen() {
    }

    public static void main(String[] var0) {
        System.out.println(UUID.randomUUID());
    }

    private static HashMap<Character, Integer> getDecompilerMap() {
        HashMap var0 = new HashMap(16);
        var0.put('0', 0);
        var0.put('1', 1);
        var0.put('2', 2);
        var0.put('3', 3);
        var0.put('4', 4);
        var0.put('5', 5);
        var0.put('6', 6);
        var0.put('7', 7);
        var0.put('8', 8);
        var0.put('9', 9);
        var0.put('A', 10);
        var0.put('B', 11);
        var0.put('C', 12);
        var0.put('D', 13);
        var0.put('E', 14);
        var0.put('F', 15);
        return var0;
    }

    private static HashMap<Integer, Character> getCompilerMap() {
        HashMap var0 = new HashMap(16);
        var0.put(0, '0');
        var0.put(1, '1');
        var0.put(2, '2');
        var0.put(3, '3');
        var0.put(4, '4');
        var0.put(5, '5');
        var0.put(6, '6');
        var0.put(7, '7');
        var0.put(8, '8');
        var0.put(9, '9');
        var0.put(10, 'A');
        var0.put(11, 'B');
        var0.put(12, 'C');
        var0.put(13, 'D');
        var0.put(14, 'E');
        var0.put(15, 'F');
        return var0;
    }

    private static char[] invertCharArray(char[] var0) {
        char[] var1 = Arrays.copyOf(var0, var0.length);

        for(int var2 = var0.length - 1; var2 >= 0; --var2) {
            var1[var2] = var0[var0.length - 1 - var2];
        }

        return var1;
    }

    public static int hexadecimalDecompiler(String var0) {
        var0 = var0.toUpperCase();
        boolean var1 = false;
        if (var0.contains("-")) {
            var0.replace("-", "");
            var1 = true;
        }

        char[] var2 = invertCharArray(var0.toCharArray());
        int var3 = 0;

        for(int var5 = 0; var5 < var2.length; ++var5) {
            char var4 = var2[var5];
            if (getDecompilerMap().containsKey(var4)) {
                int var6 = (int)Math.pow(16.0, (double)var5) * (Integer)getDecompilerMap().get(var4);
                var3 += var6;
            }
        }

        return var1 ? -1 * var3 : var3;
    }

    public static String hexadecimalCompiler(int var0) {
        boolean var1 = var0 < 0;
        if (var1) {
            var0 *= -1;
        }

        int var2 = 0;

        int var3;
        for(var3 = 1; var0 > var3; System.out.println("" + var0 + ", " + var2 + ", " + var3)) {
            var3 = (int)Math.pow(16.0, (double)var2);
            if (var0 > var3) {
                ++var2;
            }
        }

        char[] var4 = new char[var2];
        --var2;
        System.out.println("Entering Main Loop");

        int var5;
        while(var0 > 0) {
            var3 = (int)Math.pow(16.0, (double)(var2 + 1));
            var5 = var3;
            int var6 = 1;

            while(var0 > var5) {
                var5 = var3 * var6;
                if (var0 > var5) {
                    ++var6;
                }
            }

            if (var5 != var0) {
                --var6;
            }

            var5 = var3 * var6;
            var4[var2] = (Character)getCompilerMap().get(var6);
            var0 -= var5;
            --var2;
            System.out.println(Arrays.toString(var4));
        }

        for(var5 = 0; var5 < var4.length; ++var5) {
            System.out.println("'" + var4[var5] + "'");
            if (!getDecompilerMap().containsKey(var4[var5])) {
                var4[var5] = '0';
            }
        }

        var4 = invertCharArray(var4);
        String var7 = charArrayToString(var4);
        return var1 ? "-" + var7 : var7;
    }

    private static String charArrayToString(char[] var0) {
        String var1 = "";
        char[] var2 = var0;
        int var3 = var0.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            char var5 = var2[var4];
            var1 = var1 + var5;
        }

        return var1;
    }
}
