/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.vmw.ws10_102_27.ranking;

/**
 *
 * @author gizmo
 */
public class ColorMapping {

    public ColorMapping() {
    }

    public static int getNearestColorIndex(int r, int g, int b) {
        // 0, 51, 102, 153, 204, 255
        // 0-25, 26-76, 77-127, 128-178, 179-229, 230-255
        if (r >= 0 && r <= 25) {
            if (g >= 0 && g <= 25) {
                if (b >= 0 && b <= 25) {             return 0;
                } else if (b >= 26 && b <= 76) {      return 1;
                } else if (b >= 77 && b <= 127) {     return 2;
                } else if (b >= 128 && b <= 178) {    return 3;
                } else if (b >= 179 && b <= 229) {    return 4;
                } else if (b >= 230 && b <= 255) {   return 5;
                }
            } else if (g >= 26 && g <= 76) {
                if (b >= 0 && b <= 25) {             return 6;
                } else if (b >= 26 && b <= 76) {      return 7;
                } else if (b >= 77 && b <= 127) {     return 8;
                } else if (b >= 128 && b <= 178) {    return 9;
                } else if (b >= 179 && b <= 229) {    return 10;
                } else if (b >= 230 && b <= 255) {   return 11;
                }

            } else if (g >= 77 && g <= 127) {
                if (b >= 0 && b <= 25) {             return 12;
                } else if (b >= 26 && b <= 76) {      return 13;
                } else if (b >= 77 && b <= 127) {     return 14;
                } else if (b >= 128 && b <= 178) {    return 15;
                } else if (b >= 179 && b <= 229) {    return 16;
                } else if (b >= 230 && b <= 255) {   return 17;
                }

            } else if (g >= 128 && g <= 178) {
                if (b >= 0 && b <= 25) {             return 18;
                } else if (b >= 26 && b <= 76) {      return 19;
                } else if (b >= 77 && b <= 127) {     return 20;
                } else if (b >= 128 && b <= 178) {    return 21;
                } else if (b >= 179 && b <= 229) {    return 22;
                } else if (b >= 230 && b <= 255) {   return 23;
                }

            } else if (g >= 179 && g <= 229) {
                if (b >= 0 && b <= 25) {             return 24;
                } else if (b >= 26 && b <= 76) {      return 25;
                } else if (b >= 77 && b <= 127) {     return 26;
                } else if (b >= 128 && b <= 178) {    return 27;
                } else if (b >= 179 && b <= 229) {    return 28;
                } else if (b >= 230 && b <= 255) {   return 29;
                }

            } else if (g >= 230 && g <= 255) {
                if (b >= 0 && b <= 25) {             return 30;
                } else if (b >= 26 && b <= 76) {      return 31;
                } else if (b >= 77 && b <= 127) {     return 32;
                } else if (b >= 128 && b <= 178) {    return 33;
                } else if (b >= 179 && b <= 229) {    return 34;
                } else if (b >= 230 && b <= 255) {   return 35;
                }

            }
        } else if (r >= 26 && r <= 76) {
            if (g >= 0 && g <= 25) {
                if (b >= 0 && b <= 25) {             return 36;
                } else if (b >= 26 && b <= 76) {      return 37;
                } else if (b >= 77 && b <= 127) {     return 38;
                } else if (b >= 128 && b <= 178) {    return 39;
                } else if (b >= 179 && b <= 229) {    return 40;
                } else if (b >= 230 && b <= 255) {   return 41;
                }

            } else if (g >= 26 && g <= 76) {
                if (b >= 0 && b <= 25) {             return 42;
                } else if (b >= 26 && b <= 76) {      return 43;
                } else if (b >= 77 && b <= 127) {     return 44;
                } else if (b >= 128 && b <= 178) {    return 45;
                } else if (b >= 179 && b <= 229) {    return 46;
                } else if (b >= 230 && b <= 255) {   return 47;
                }

            } else if (g >= 77 && g <= 127) {
                if (b >= 0 && b <= 25) {             return 48;
                } else if (b >= 26 && b <= 76) {      return 49;
                } else if (b >= 77 && b <= 127) {     return 50;
                } else if (b >= 128 && b <= 178) {    return 51;
                } else if (b >= 179 && b <= 229) {    return 52;
                } else if (b >= 230 && b <= 255) {   return 53;
                }

            } else if (g >= 128 && g <= 178) {
                if (b >= 0 && b <= 25) {             return 54;
                } else if (b >= 26 && b <= 76) {      return 55;
                } else if (b >= 77 && b <= 127) {     return 56;
                } else if (b >= 128 && b <= 178) {    return 57;
                } else if (b >= 179 && b <= 229) {    return 58;
                } else if (b >= 230 && b <= 255) {   return 59;
                }

            } else if (g >= 179 && g <= 229) {
                if (b >= 0 && b <= 25) {             return 60;
                } else if (b >= 26 && b <= 76) {      return 61;
                } else if (b >= 77 && b <= 127) {     return 62;
                } else if (b >= 128 && b <= 178) {    return 63;
                } else if (b >= 179 && b <= 229) {    return 64;
                } else if (b >= 230 && b <= 255) {   return 65;
                }

            } else if (g >= 230 && g <= 255) {
                if (b >= 0 && b <= 25) {             return 66;
                } else if (b >= 26 && b <= 76) {      return 67;
                } else if (b >= 77 && b <= 127) {     return 68;
                } else if (b >= 128 && b <= 178) {    return 69;
                } else if (b >= 179 && b <= 229) {    return 70;
                } else if (b >= 230 && b <= 255) {   return 71;
                }

            }
        } else if (r >= 77 && r <= 127) {
            if (g >= 0 && g <= 25) {
                if (b >= 0 && b <= 25) {             return 72;
                } else if (b >= 26 && b <= 76) {      return 73;
                } else if (b >= 77 && b <= 127) {     return 74;
                } else if (b >= 128 && b <= 178) {    return 75;
                } else if (b >= 179 && b <= 229) {    return 76;
                } else if (b >= 230 && b <= 255) {   return 77;
                }

            } else if (g >= 26 && g <= 76) {
                if (b >= 0 && b <= 25) {             return 78;
                } else if (b >= 26 && b <= 76) {      return 79;
                } else if (b >= 77 && b <= 127) {     return 80;
                } else if (b >= 128 && b <= 178) {    return 81;
                } else if (b >= 179 && b <= 229) {    return 82;
                } else if (b >= 230 && b <= 255) {   return 83;
                }

            } else if (g >= 77 && g <= 127) {
                if (b >= 0 && b <= 25) {             return 84;
                } else if (b >= 26 && b <= 76) {      return 85;
                } else if (b >= 77 && b <= 127) {     return 86;
                } else if (b >= 128 && b <= 178) {    return 87;
                } else if (b >= 179 && b <= 229) {    return 88;
                } else if (b >= 230 && b <= 255) {   return 89;
                }

            } else if (g >= 128 && g <= 178) {
                if (b >= 0 && b <= 25) {             return 90;
                } else if (b >= 26 && b <= 76) {      return 91;
                } else if (b >= 77 && b <= 127) {     return 92;
                } else if (b >= 128 && b <= 178) {    return 93;
                } else if (b >= 179 && b <= 229) {    return 94;
                } else if (b >= 230 && b <= 255) {   return 95;
                }

            } else if (g >= 179 && g <= 229) {
                if (b >= 0 && b <= 25) {             return 96;
                } else if (b >= 26 && b <= 76) {      return 97;
                } else if (b >= 77 && b <= 127) {     return 98;
                } else if (b >= 128 && b <= 178) {    return 99;
                } else if (b >= 179 && b <= 229) {    return 100;
                } else if (b >= 230 && b <= 255) {   return 101;
                }

            } else if (g >= 230 && g <= 255) {
                if (b >= 0 && b <= 25) {             return 102;
                } else if (b >= 26 && b <= 76) {      return 103;
                } else if (b >= 77 && b <= 127) {     return 104;
                } else if (b >= 128 && b <= 178) {    return 105;
                } else if (b >= 179 && b <= 229) {    return 106;
                } else if (b >= 230 && b <= 255) {   return 107;
                }

            }
        } else if (r >= 128 && r <= 178) {
            if (g >= 0 && g <= 25) {
                if (b >= 0 && b <= 25) {             return 108;
                } else if (b >= 26 && b <= 76) {      return 109;
                } else if (b >= 77 && b <= 127) {     return 110;
                } else if (b >= 128 && b <= 178) {    return 111;
                } else if (b >= 179 && b <= 229) {    return 112;
                } else if (b >= 230 && b <= 255) {   return 113;
                }

            } else if (g >= 26 && g <= 76) {
                if (b >= 0 && b <= 25) {             return 114;
                } else if (b >= 26 && b <= 76) {      return 115;
                } else if (b >= 77 && b <= 127) {     return 116;
                } else if (b >= 128 && b <= 178) {    return 117;
                } else if (b >= 179 && b <= 229) {    return 118;
                } else if (b >= 230 && b <= 255) {   return 119;
                }

            } else if (g >= 77 && g <= 127) {
                if (b >= 0 && b <= 25) {             return 119;
                } else if (b >= 26 && b <= 76) {      return 120;
                } else if (b >= 77 && b <= 127) {     return 121;
                } else if (b >= 128 && b <= 178) {    return 122;
                } else if (b >= 179 && b <= 229) {    return 123;
                } else if (b >= 230 && b <= 255) {   return 124;
                }

            } else if (g >= 128 && g <= 178) {
                if (b >= 0 && b <= 25) {             return 125;
                } else if (b >= 26 && b <= 76) {      return 126;
                } else if (b >= 77 && b <= 127) {     return 127;
                } else if (b >= 128 && b <= 178) {    return 128;
                } else if (b >= 179 && b <= 229) {    return 129;
                } else if (b >= 230 && b <= 255) {   return 130;
                }

            } else if (g >= 179 && g <= 229) {
                if (b >= 0 && b <= 25) {             return 131;
                } else if (b >= 26 && b <= 76) {      return 132;
                } else if (b >= 77 && b <= 127) {     return 133;
                } else if (b >= 128 && b <= 178) {    return 134;
                } else if (b >= 179 && b <= 229) {    return 135;
                } else if (b >= 230 && b <= 255) {   return 136;
                }

            } else if (g >= 230 && g <= 255) {
                if (b >= 0 && b <= 25) {             return 137;
                } else if (b >= 26 && b <= 76) {      return 138;
                } else if (b >= 77 && b <= 127) {     return 139;
                } else if (b >= 128 && b <= 178) {    return 140;
                } else if (b >= 179 && b <= 229) {    return 141;
                } else if (b >= 230 && b <= 255) {   return 142;
                }

            }
        } else if (r >= 179 && r <= 229) {
            if (g >= 0 && g <= 25) {
                if (b >= 0 && b <= 25) {             return 143;
                } else if (b >= 26 && b <= 76) {      return 144;
                } else if (b >= 77 && b <= 127) {     return 145;
                } else if (b >= 128 && b <= 178) {    return 146;
                } else if (b >= 179 && b <= 229) {    return 147;
                } else if (b >= 230 && b <= 255) {   return 148;
                }

            } else if (g >= 26 && g <= 76) {
                if (b >= 0 && b <= 25) {             return 149;
                } else if (b >= 26 && b <= 76) {      return 150;
                } else if (b >= 77 && b <= 127) {     return 151;
                } else if (b >= 128 && b <= 178) {    return 152;
                } else if (b >= 179 && b <= 229) {    return 153;
                } else if (b >= 230 && b <= 255) {   return 154;
                }

            } else if (g >= 77 && g <= 127) {
                if (b >= 0 && b <= 25) {             return 155;
                } else if (b >= 26 && b <= 76) {      return 156;
                } else if (b >= 77 && b <= 127) {     return 157;
                } else if (b >= 128 && b <= 178) {    return 158;
                } else if (b >= 179 && b <= 229) {    return 159;
                } else if (b >= 230 && b <= 255) {   return 160;
                }

            } else if (g >= 128 && g <= 178) {
                if (b >= 0 && b <= 25) {             return 161;
                } else if (b >= 26 && b <= 76) {      return 162;
                } else if (b >= 77 && b <= 127) {     return 163;
                } else if (b >= 128 && b <= 178) {    return 164;
                } else if (b >= 179 && b <= 229) {    return 165;
                } else if (b >= 230 && b <= 255) {   return 166;
                }

            } else if (g >= 179 && g <= 229) {
                if (b >= 0 && b <= 25) {             return 167;
                } else if (b >= 26 && b <= 76) {      return 168;
                } else if (b >= 77 && b <= 127) {     return 169;
                } else if (b >= 128 && b <= 178) {    return 170;
                } else if (b >= 179 && b <= 229) {    return 171;
                } else if (b >= 230 && b <= 255) {   return 172;
                }

            } else if (g >= 230 && g <= 255) {
                if (b >= 0 && b <= 25) {             return 173;
                } else if (b >= 26 && b <= 76) {      return 174;
                } else if (b >= 77 && b <= 127) {     return 175;
                } else if (b >= 128 && b <= 178) {    return 176;
                } else if (b >= 179 && b <= 229) {    return 177;
                } else if (b >= 230 && b <= 255) {   return 178;
                }

            }
        } else if (r >= 230 && r <= 255) {
            if (g >= 0 && g <= 25) {
                if (b >= 0 && b <= 25) {             return 179;
                } else if (b >= 26 && b <= 76) {      return 180;
                } else if (b >= 77 && b <= 127) {     return 181;
                } else if (b >= 128 && b <= 178) {    return 182;
                } else if (b >= 179 && b <= 229) {    return 183;
                } else if (b >= 230 && b <= 255) {   return 184;
                }

            } else if (g >= 26 && g <= 76) {
                if (b >= 0 && b <= 25) {             return 185;
                } else if (b >= 26 && b <= 76) {      return 186;
                } else if (b >= 77 && b <= 127) {     return 187;
                } else if (b >= 128 && b <= 178) {    return 188;
                } else if (b >= 179 && b <= 229) {    return 189;
                } else if (b >= 230 && b <= 255) {   return 190;
                }

            } else if (g >= 77 && g <= 127) {
                if (b >= 0 && b <= 25) {             return 191;
                } else if (b >= 26 && b <= 76) {      return 192;
                } else if (b >= 77 && b <= 127) {     return 193;
                } else if (b >= 128 && b <= 178) {    return 194;
                } else if (b >= 179 && b <= 229) {    return 195;
                } else if (b >= 230 && b <= 255) {   return 196;
                }

            } else if (g >= 128 && g <= 178) {
                if (b >= 0 && b <= 25) {             return 197;
                } else if (b >= 26 && b <= 76) {      return 198;
                } else if (b >= 77 && b <= 127) {     return 199;
                } else if (b >= 128 && b <= 178) {    return 200;
                } else if (b >= 179 && b <= 229) {    return 201;
                } else if (b >= 230 && b <= 255) {   return 202;
                }

            } else if (g >= 179 && g <= 229) {
                if (b >= 0 && b <= 25) {             return 203;
                } else if (b >= 26 && b <= 76) {      return 204;
                } else if (b >= 77 && b <= 127) {     return 205;
                } else if (b >= 128 && b <= 178) {    return 206;
                } else if (b >= 179 && b <= 229) {    return 207;
                } else if (b >= 230 && b <= 255) {   return 208;
                }

            } else if (g >= 230 && g <= 255) {
                if (b >= 0 && b <= 25) {             return 209;
                } else if (b >= 26 && b <= 76) {      return 210;
                } else if (b >= 77 && b <= 127) {     return 211;
                } else if (b >= 128 && b <= 178) {    return 212;
                } else if (b >= 179 && b <= 229) {    return 213;
                } else if (b >= 230 && b <= 255) {   return 214;
                }

            }
        }

        return 0;
    }
}
