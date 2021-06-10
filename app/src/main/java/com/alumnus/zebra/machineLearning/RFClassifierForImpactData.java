package com.alumnus.zebra.machineLearning;

public class RFClassifierForImpactData {
    public static int predict_0(double[] features) {
        int[] classes = new int[3];

        if (features[178] <= -6.9857940673828125) {
            if (features[119] <= 8.482749938964844) {
                if (features[8] <= 7.561546325683594) {
                    if (features[171] <= 8.137298583984375) {
                        if (features[203] <= 23.29877471923828) {
                            classes[0] = 0;
                            classes[1] = 4;
                            classes[2] = 0;
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 4;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 33;
                    classes[2] = 0;
                }
            } else {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 19;
            }
        } else {
            if (features[35] <= 9.288803100585938) {
                classes[0] = 0;
                classes[1] = 3;
                classes[2] = 0;
            } else {
                classes[0] = 38;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_1(double[] features) {
        int[] classes = new int[3];

        if (features[7] <= 0.767669677734375) {
            classes[0] = 30;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[280] <= -2.5716934204101562) {
                if (features[200] <= 37.922882080078125) {
                    if (features[195] <= 6.870643615722656) {
                        classes[0] = 0;
                        classes[1] = 3;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 2;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 21;
                }
            } else {
                if (features[125] <= 7.1393280029296875) {
                    classes[0] = 0;
                    classes[1] = 39;
                    classes[2] = 0;
                } else {
                    if (features[133] <= 14.816024780273438) {
                        classes[0] = 0;
                        classes[1] = 5;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 2;
                    }
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_2(double[] features) {
        int[] classes = new int[3];

        if (features[19] <= 0.767669677734375) {
            if (features[264] <= -2.303009033203125) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 37;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[103] <= -0.153533935546875) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 16;
            } else {
                if (features[136] <= 11.054443359375) {
                    classes[0] = 0;
                    classes[1] = 34;
                    classes[2] = 0;
                } else {
                    if (features[140] <= -4.9898529052734375) {
                        if (features[63] <= 9.672637939453125) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 2;
                        } else {
                            classes[0] = 0;
                            classes[1] = 5;
                            classes[2] = 0;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 7;
                    }
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_3(double[] features) {
        int[] classes = new int[3];

        if (features[245] <= -2.2646255493164062) {
            if (features[274] <= -0.8060531616210938) {
                if (features[19] <= 7.2928619384765625) {
                    if (features[189] <= 0.11515045166015625) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 32;
                    } else {
                        classes[0] = 0;
                        classes[1] = 9;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 8;
                    classes[2] = 0;
                }
            } else {
                classes[0] = 0;
                classes[1] = 21;
                classes[2] = 0;
            }
        } else {
            classes[0] = 32;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_4(double[] features) {
        int[] classes = new int[3];

        if (features[63] <= 0.8828201293945312) {
            classes[0] = 31;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[116] <= 7.9069976806640625) {
                if (features[265] <= -6.0645904541015625) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 1;
                } else {
                    classes[0] = 0;
                    classes[1] = 39;
                    classes[2] = 0;
                }
            } else {
                if (features[158] <= -28.941146850585938) {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 30;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_5(double[] features) {
        int[] classes = new int[3];

        if (features[284] <= 8.482749938964844) {
            if (features[122] <= 4.9898529052734375) {
                if (features[238] <= -21.264450073242188) {
                    if (features[265] <= -6.0645904541015625) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    } else {
                        classes[0] = 0;
                        classes[1] = 43;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 2;
                }
            } else {
                if (features[201] <= -5.83428955078125) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 18;
                } else {
                    if (features[212] <= 33.50878143310547) {
                        classes[0] = 0;
                        classes[1] = 4;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                }
            }
        } else {
            classes[0] = 33;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_6(double[] features) {
        int[] classes = new int[3];

        if (features[287] <= 7.830230712890625) {
            if (features[136] <= 13.549369812011719) {
                if (features[149] <= 7.06256103515625) {
                    if (features[114] <= 23.64422607421875) {
                        classes[0] = 0;
                        classes[1] = 38;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 5;
                }
            } else {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 19;
            }
        } else {
            if (features[89] <= 9.020118713378906) {
                classes[0] = 0;
                classes[1] = 2;
                classes[2] = 0;
            } else {
                classes[0] = 37;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_7(double[] features) {
        int[] classes = new int[3];

        if (features[275] <= 9.135269165039062) {
            if (features[298] <= -3.1474456787109375) {
                if (features[190] <= -38.805702209472656) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 27;
                } else {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                }
            } else {
                if (features[94] <= -0.30706787109375) {
                    if (features[3] <= 0.2303009033203125) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 4;
                    } else {
                        classes[0] = 0;
                        classes[1] = 7;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 32;
                    classes[2] = 0;
                }
            }
        } else {
            if (features[38] <= 9.442337036132812) {
                if (features[298] <= -20.61193084716797) {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 1;
                }
            } else {
                classes[0] = 29;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_8(double[] features) {
        int[] classes = new int[3];

        if (features[175] <= -1.8040237426757812) {
            if (features[95] <= 10.939292907714844) {
                if (features[54] <= 1.1898880004882812) {
                    classes[0] = 1;
                    classes[1] = 0;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 34;
                    classes[2] = 0;
                }
            } else {
                if (features[49] <= 6.102973937988281) {
                    if (features[67] <= 6.640342712402344) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 23;
                    } else {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    }
                } else {
                    if (features[299] <= 1.6121063232421875) {
                        classes[0] = 0;
                        classes[1] = 14;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 4;
                    }
                }
            }
        } else {
            classes[0] = 24;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_9(double[] features) {
        int[] classes = new int[3];

        if (features[266] <= 9.979705810546875) {
            if (features[195] <= -11.207977294921875) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 17;
            } else {
                if (features[255] <= -8.981735229492188) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 3;
                } else {
                    if (features[10] <= 8.252449035644531) {
                        classes[0] = 0;
                        classes[1] = 45;
                        classes[2] = 0;
                    } else {
                        if (features[63] <= 7.2928619384765625) {
                            classes[0] = 0;
                            classes[1] = 1;
                            classes[2] = 0;
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        }
                    }
                }
            }
        } else {
            if (features[65] <= 13.280685424804688) {
                classes[0] = 34;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 1;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_10(double[] features) {
        int[] classes = new int[3];

        if (features[22] <= 0.7292861938476562) {
            classes[0] = 27;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[136] <= 15.66046142578125) {
                if (features[283] <= -2.76361083984375) {
                    if (features[146] <= -4.490867614746094) {
                        classes[0] = 0;
                        classes[1] = 5;
                        classes[2] = 0;
                    } else {
                        if (features[91] <= 4.759552001953125) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 4;
                        } else {
                            classes[0] = 0;
                            classes[1] = 1;
                            classes[2] = 0;
                        }
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 38;
                    classes[2] = 0;
                }
            } else {
                if (features[101] <= 9.979705810546875) {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 26;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_11(double[] features) {
        int[] classes = new int[3];

        if (features[233] <= 0.6909027099609375) {
            if (features[44] <= 12.129180908203125) {
                if (features[198] <= -10.517074584960938) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 22;
                } else {
                    if (features[267] <= -2.1110916137695312) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 5;
                    } else {
                        classes[0] = 0;
                        classes[1] = 6;
                        classes[2] = 0;
                    }
                }
            } else {
                if (features[249] <= 14.700874328613281) {
                    classes[0] = 0;
                    classes[1] = 40;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 1;
                }
            }
        } else {
            classes[0] = 28;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_12(double[] features) {
        int[] classes = new int[3];

        if (features[281] <= 8.444366455078125) {
            if (features[198] <= -14.969558715820312) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 23;
            } else {
                if (features[98] <= 21.18768310546875) {
                    classes[0] = 0;
                    classes[1] = 42;
                    classes[2] = 0;
                } else {
                    if (features[205] <= -19.844261169433594) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 2;
                    } else {
                        classes[0] = 0;
                        classes[1] = 3;
                        classes[2] = 0;
                    }
                }
            }
        } else {
            if (features[267] <= 4.030265808105469) {
                classes[0] = 31;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_13(double[] features) {
        int[] classes = new int[3];

        if (features[13] <= 0.8444366455078125) {
            if (features[151] <= -6.793876647949219) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 25;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[192] <= -0.42221832275390625) {
                if (features[6] <= 0.767669677734375) {
                    if (features[44] <= 12.205947875976562) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 31;
                    } else {
                        if (features[177] <= 5.9110565185546875) {
                            classes[0] = 0;
                            classes[1] = 2;
                            classes[2] = 0;
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        }
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 11;
                    classes[2] = 0;
                }
            } else {
                classes[0] = 0;
                classes[1] = 31;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_14(double[] features) {
        int[] classes = new int[3];

        if (features[40] <= 0.767669677734375) {
            if (features[288] <= -0.9595870971679688) {
                classes[0] = 0;
                classes[1] = 2;
                classes[2] = 0;
            } else {
                classes[0] = 37;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[104] <= 14.470573425292969) {
                if (features[149] <= 7.06256103515625) {
                    classes[0] = 0;
                    classes[1] = 33;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 3;
                }
            } else {
                if (features[138] <= -3.4545135498046875) {
                    classes[0] = 0;
                    classes[1] = 5;
                    classes[2] = 0;
                } else {
                    if (features[151] <= -23.64422607421875) {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 21;
                    }
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_15(double[] features) {
        int[] classes = new int[3];

        if (features[233] <= 0.6909027099609375) {
            if (features[139] <= 13.549369812011719) {
                if (features[291] <= 0.11515045166015625) {
                    if (features[255] <= -12.551399230957031) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    } else {
                        classes[0] = 0;
                        classes[1] = 45;
                        classes[2] = 0;
                    }
                } else {
                    if (features[93] <= 2.5333099365234375) {
                        classes[0] = 0;
                        classes[1] = 4;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 4;
                    }
                }
            } else {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 19;
            }
        } else {
            classes[0] = 29;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_16(double[] features) {
        int[] classes = new int[3];

        if (features[13] <= 0.767669677734375) {
            classes[0] = 26;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[147] <= 19.422042846679688) {
                if (features[195] <= -2.3413925170898438) {
                    if (features[299] <= 0.5373687744140625) {
                        if (features[221] <= 14.624107360839844) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        } else {
                            classes[0] = 0;
                            classes[1] = 6;
                            classes[2] = 0;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 7;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 35;
                    classes[2] = 0;
                }
            } else {
                if (features[15] <= 2.4949264526367188) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 26;
                } else {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_17(double[] features) {
        int[] classes = new int[3];

        if (features[54] <= -0.11515045166015625) {
            if (features[6] <= -0.8444366455078125) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 26;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[195] <= -3.0322952270507812) {
                if (features[256] <= 6.102973937988281) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 29;
                } else {
                    if (features[153] <= 19.65234375) {
                        classes[0] = 0;
                        classes[1] = 6;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                }
            } else {
                if (features[149] <= 10.286773681640625) {
                    classes[0] = 0;
                    classes[1] = 38;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 1;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_18(double[] features) {
        int[] classes = new int[3];

        if (features[242] <= -11.591812133789062) {
            if (features[201] <= -8.137298583984375) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 25;
            } else {
                if (features[130] <= 16.389747619628906) {
                    if (features[145] <= -5.987823486328125) {
                        classes[0] = 0;
                        classes[1] = 29;
                        classes[2] = 0;
                    } else {
                        if (features[148] <= -5.6039886474609375) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 2;
                        } else {
                            classes[0] = 0;
                            classes[1] = 8;
                            classes[2] = 0;
                        }
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 5;
                }
            }
        } else {
            if (features[111] <= -3.531280517578125) {
                classes[0] = 0;
                classes[1] = 3;
                classes[2] = 0;
            } else {
                classes[0] = 30;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_19(double[] features) {
        int[] classes = new int[3];

        if (features[175] <= -4.8363189697265625) {
            if (features[95] <= 11.706962585449219) {
                if (features[229] <= -39.189537048339844) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 2;
                } else {
                    classes[0] = 0;
                    classes[1] = 32;
                    classes[2] = 0;
                }
            } else {
                if (features[28] <= 6.44842529296875) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 23;
                } else {
                    if (features[142] <= 17.426101684570312) {
                        classes[0] = 0;
                        classes[1] = 6;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                }
            }
        } else {
            if (features[248] <= 4.183799743652344) {
                classes[0] = 0;
                classes[1] = 3;
                classes[2] = 0;
            } else {
                classes[0] = 35;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_20(double[] features) {
        int[] classes = new int[3];

        if (features[22] <= 0.7292861938476562) {
            classes[0] = 22;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[298] <= -3.6080474853515625) {
                if (features[249] <= 7.638313293457031) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 28;
                } else {
                    classes[0] = 0;
                    classes[1] = 2;
                    classes[2] = 0;
                }
            } else {
                if (features[200] <= 39.151153564453125) {
                    classes[0] = 0;
                    classes[1] = 28;
                    classes[2] = 0;
                } else {
                    if (features[216] <= -10.401924133300781) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 3;
                    } else {
                        if (features[119] <= -5.9110565185546875) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        } else {
                            if (features[106] <= 0.8444366455078125) {
                                classes[0] = 0;
                                classes[1] = 0;
                                classes[2] = 1;
                            } else {
                                classes[0] = 0;
                                classes[1] = 17;
                                classes[2] = 0;
                            }
                        }
                    }
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_21(double[] features) {
        int[] classes = new int[3];

        if (features[22] <= 0.7292861938476562) {
            classes[0] = 35;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[136] <= 13.549369812011719) {
                if (features[147] <= 32.357276916503906) {
                    classes[0] = 0;
                    classes[1] = 39;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 1;
                }
            } else {
                if (features[212] <= 27.559341430664062) {
                    classes[0] = 0;
                    classes[1] = 2;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 25;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_22(double[] features) {
        int[] classes = new int[3];

        if (features[248] <= -0.7292861938476562) {
            if (features[150] <= 18.69275665283203) {
                if (features[279] <= -1.228271484375) {
                    if (features[290] <= 0.8828201293945312) {
                        classes[0] = 0;
                        classes[1] = 7;
                        classes[2] = 0;
                    } else {
                        if (features[47] <= 26.024002075195312) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 4;
                        } else {
                            classes[0] = 0;
                            classes[1] = 1;
                            classes[2] = 0;
                        }
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 34;
                    classes[2] = 0;
                }
            } else {
                if (features[119] <= 6.640342712402344) {
                    classes[0] = 0;
                    classes[1] = 4;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 25;
                }
            }
        } else {
            if (features[218] <= 12.58978271484375) {
                classes[0] = 26;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 1;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_23(double[] features) {
        int[] classes = new int[3];

        if (features[251] <= 6.486808776855469) {
            if (features[101] <= 15.545310974121094) {
                if (features[106] <= 0.8444366455078125) {
                    if (features[6] <= 3.224212646484375) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 5;
                    } else {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    }
                } else {
                    if (features[164] <= -7.2928619384765625) {
                        if (features[160] <= -27.90479278564453) {
                            classes[0] = 0;
                            classes[1] = 2;
                            classes[2] = 0;
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 2;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 34;
                        classes[2] = 0;
                    }
                }
            } else {
                if (features[34] <= 8.405982971191406) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 26;
                } else {
                    classes[0] = 0;
                    classes[1] = 3;
                    classes[2] = 0;
                }
            }
        } else {
            if (features[127] <= 5.642372131347656) {
                classes[0] = 27;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_24(double[] features) {
        int[] classes = new int[3];

        if (features[7] <= 0.767669677734375) {
            if (features[270] <= 5.719139099121094) {
                classes[0] = 34;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            }
        } else {
            if (features[142] <= 12.090797424316406) {
                if (features[112] <= 8.137298583984375) {
                    classes[0] = 0;
                    classes[1] = 35;
                    classes[2] = 0;
                } else {
                    if (features[274] <= -2.1878585815429688) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 2;
                    } else {
                        classes[0] = 0;
                        classes[1] = 4;
                        classes[2] = 0;
                    }
                }
            } else {
                if (features[52] <= 8.713050842285156) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 24;
                } else {
                    classes[0] = 0;
                    classes[1] = 2;
                    classes[2] = 0;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_25(double[] features) {
        int[] classes = new int[3];

        if (features[69] <= 0.6909027099609375) {
            if (features[91] <= 3.224212646484375) {
                classes[0] = 26;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 3;
                classes[2] = 0;
            }
        } else {
            if (features[50] <= 15.391777038574219) {
                if (features[40] <= 6.333274841308594) {
                    if (features[144] <= 1.0363540649414062) {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 33;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 9;
                    classes[2] = 0;
                }
            } else {
                classes[0] = 0;
                classes[1] = 30;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_26(double[] features) {
        int[] classes = new int[3];

        if (features[287] <= 9.020118713378906) {
            if (features[119] <= 8.482749938964844) {
                if (features[195] <= -20.57354736328125) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 5;
                } else {
                    if (features[264] <= -3.0322952270507812) {
                        if (features[274] <= -1.7656402587890625) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 3;
                        } else {
                            classes[0] = 0;
                            classes[1] = 2;
                            classes[2] = 0;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 41;
                        classes[2] = 0;
                    }
                }
            } else {
                if (features[291] <= -2.6484603881835938) {
                    if (features[204] <= 26.868438720703125) {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 25;
                }
            }
        } else {
            classes[0] = 23;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_27(double[] features) {
        int[] classes = new int[3];

        if (features[233] <= 3.4161300659179688) {
            if (features[293] <= 2.1110916137695312) {
                if (features[283] <= -6.947410583496094) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 7;
                } else {
                    if (features[136] <= 17.579635620117188) {
                        if (features[255] <= -8.981735229492188) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        } else {
                            if (features[252] <= 9.403953552246094) {
                                classes[0] = 0;
                                classes[1] = 50;
                                classes[2] = 0;
                            } else {
                                if (features[16] <= 5.4504547119140625) {
                                    classes[0] = 0;
                                    classes[1] = 2;
                                    classes[2] = 0;
                                } else {
                                    classes[0] = 0;
                                    classes[1] = 0;
                                    classes[2] = 1;
                                }
                            }
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 3;
                    }
                }
            } else {
                if (features[252] <= 4.260566711425781) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 11;
                } else {
                    if (features[160] <= -39.07438659667969) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 2;
                    } else {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    }
                }
            }
        } else {
            classes[0] = 23;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_28(double[] features) {
        int[] classes = new int[3];

        if (features[66] <= 0.11515045166015625) {
            if (features[40] <= 0.767669677734375) {
                classes[0] = 22;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 2;
                classes[2] = 0;
            }
        } else {
            if (features[292] <= -1.2666549682617188) {
                if (features[62] <= 18.07862091064453) {
                    if (features[101] <= 2.0343246459960938) {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 41;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 4;
                    classes[2] = 0;
                }
            } else {
                if (features[98] <= 19.460426330566406) {
                    classes[0] = 0;
                    classes[1] = 25;
                    classes[2] = 0;
                } else {
                    if (features[40] <= 6.6787261962890625) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 3;
                    } else {
                        classes[0] = 0;
                        classes[1] = 4;
                        classes[2] = 0;
                    }
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_29(double[] features) {
        int[] classes = new int[3];

        if (features[293] <= 9.711021423339844) {
            if (features[259] <= -5.7575225830078125) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 23;
            } else {
                if (features[289] <= -5.987823486328125) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 7;
                } else {
                    if (features[188] <= 22.185653686523438) {
                        if (features[110] <= 0.5757522583007812) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        } else {
                            classes[0] = 0;
                            classes[1] = 6;
                            classes[2] = 0;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 30;
                        classes[2] = 0;
                    }
                }
            }
        } else {
            classes[0] = 35;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_30(double[] features) {
        int[] classes = new int[3];

        if (features[4] <= 0.7292861938476562) {
            classes[0] = 23;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[298] <= -0.9595870971679688) {
                if (features[34] <= 5.795906066894531) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 21;
                } else {
                    if (features[119] <= 9.979705810546875) {
                        if (features[298] <= -2.303009033203125) {
                            classes[0] = 0;
                            classes[1] = 9;
                            classes[2] = 0;
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 8;
                    }
                }
            } else {
                if (features[151] <= 6.026206970214844) {
                    classes[0] = 0;
                    classes[1] = 38;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 2;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_31(double[] features) {
        int[] classes = new int[3];

        if (features[254] <= 5.028236389160156) {
            if (features[280] <= -3.5696640014648438) {
                if (features[33] <= 4.759552001953125) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 22;
                } else {
                    classes[0] = 0;
                    classes[1] = 7;
                    classes[2] = 0;
                }
            } else {
                classes[0] = 0;
                classes[1] = 38;
                classes[2] = 0;
            }
        } else {
            if (features[169] <= -22.224037170410156) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 1;
            } else {
                classes[0] = 34;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_32(double[] features) {
        int[] classes = new int[3];

        if (features[287] <= 8.367599487304688) {
            if (features[137] <= -2.3413925170898438) {
                if (features[297] <= 0.34545135498046875) {
                    classes[0] = 0;
                    classes[1] = 41;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 2;
                }
            } else {
                if (features[148] <= 0.26868438720703125) {
                    if (features[141] <= 9.442337036132812) {
                        classes[0] = 0;
                        classes[1] = 5;
                        classes[2] = 0;
                    } else {
                        if (features[101] <= 11.246360778808594) {
                            classes[0] = 0;
                            classes[1] = 1;
                            classes[2] = 0;
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 5;
                        }
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 15;
                }
            }
        } else {
            if (features[193] <= 6.294891357421875) {
                classes[0] = 32;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_33(double[] features) {
        int[] classes = new int[3];

        if (features[7] <= 0.767669677734375) {
            if (features[100] <= 3.1474456787109375) {
                classes[0] = 28;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            }
        } else {
            if (features[283] <= -1.3434219360351562) {
                if (features[38] <= 15.622077941894531) {
                    if (features[184] <= -13.050384521484375) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 17;
                    } else {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 2;
                    classes[2] = 0;
                }
            } else {
                if (features[139] <= 13.549369812011719) {
                    classes[0] = 0;
                    classes[1] = 49;
                    classes[2] = 0;
                } else {
                    if (features[204] <= 9.902938842773438) {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 2;
                    }
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_34(double[] features) {
        int[] classes = new int[3];

        if (features[22] <= 0.7292861938476562) {
            if (features[126] <= -6.486808776855469) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 29;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[116] <= 9.135269165039062) {
                if (features[288] <= -13.050384521484375) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 2;
                } else {
                    if (features[135] <= 38.537017822265625) {
                        classes[0] = 0;
                        classes[1] = 37;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                }
            } else {
                if (features[202] <= -19.729110717773438) {
                    if (features[79] <= 6.333274841308594) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 27;
                    } else {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 4;
                    classes[2] = 0;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_35(double[] features) {
        int[] classes = new int[3];

        if (features[66] <= 0.34545135498046875) {
            if (features[264] <= 4.0686492919921875) {
                classes[0] = 31;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 2;
                classes[2] = 0;
            }
        } else {
            if (features[116] <= 8.405982971191406) {
                classes[0] = 0;
                classes[1] = 43;
                classes[2] = 0;
            } else {
                if (features[97] <= -22.914939880371094) {
                    classes[0] = 0;
                    classes[1] = 2;
                    classes[2] = 0;
                } else {
                    if (features[59] <= 23.720993041992188) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 23;
                    } else {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    }
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_36(double[] features) {
        int[] classes = new int[3];

        if (features[7] <= 0.767669677734375) {
            if (features[187] <= -7.177711486816406) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 34;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[122] <= 7.100944519042969) {
                if (features[262] <= -13.357452392578125) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 2;
                } else {
                    classes[0] = 0;
                    classes[1] = 42;
                    classes[2] = 0;
                }
            } else {
                if (features[172] <= -20.074562072753906) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 21;
                } else {
                    classes[0] = 0;
                    classes[1] = 2;
                    classes[2] = 0;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_37(double[] features) {
        int[] classes = new int[3];

        if (features[84] <= 0.03838348388671875) {
            if (features[291] <= -1.074737548828125) {
                classes[0] = 0;
                classes[1] = 3;
                classes[2] = 0;
            } else {
                classes[0] = 35;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[53] <= 18.308921813964844) {
                if (features[195] <= -3.1090621948242188) {
                    if (features[24] <= 10.939292907714844) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 31;
                    } else {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    }
                } else {
                    if (features[146] <= 6.44842529296875) {
                        classes[0] = 0;
                        classes[1] = 12;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                }
            } else {
                classes[0] = 0;
                classes[1] = 18;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_38(double[] features) {
        int[] classes = new int[3];

        if (features[125] <= 5.335304260253906) {
            if (features[270] <= -2.303009033203125) {
                if (features[112] <= 7.06256103515625) {
                    classes[0] = 0;
                    classes[1] = 2;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 3;
                }
            } else {
                if (features[150] <= 29.670433044433594) {
                    classes[0] = 0;
                    classes[1] = 44;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 1;
                }
            }
        } else {
            if (features[241] <= -11.822113037109375) {
                if (features[92] <= 14.43218994140625) {
                    if (features[81] <= 12.58978271484375) {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 5;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 23;
                }
            } else {
                classes[0] = 22;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_39(double[] features) {
        int[] classes = new int[3];

        if (features[13] <= 0.8444366455078125) {
            classes[0] = 30;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[201] <= -7.638313293457031) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 24;
            } else {
                if (features[85] <= -0.6141357421875) {
                    if (features[34] <= 5.335304260253906) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 4;
                    } else {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    }
                } else {
                    if (features[249] <= 14.700874328613281) {
                        classes[0] = 0;
                        classes[1] = 40;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 2;
                    }
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_40(double[] features) {
        int[] classes = new int[3];

        if (features[150] <= 1.4969558715820312) {
            classes[0] = 33;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[95] <= 14.585723876953125) {
                if (features[283] <= -2.0727081298828125) {
                    if (features[198] <= 1.7656402587890625) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 4;
                    } else {
                        classes[0] = 0;
                        classes[1] = 5;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 30;
                    classes[2] = 0;
                }
            } else {
                if (features[153] <= 9.749404907226562) {
                    if (features[241] <= -34.3916015625) {
                        classes[0] = 0;
                        classes[1] = 3;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 26;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_41(double[] features) {
        int[] classes = new int[3];

        if (features[278] <= 8.713050842285156) {
            if (features[195] <= -11.207977294921875) {
                if (features[251] <= -35.38957214355469) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 17;
                } else {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                }
            } else {
                if (features[289] <= -17.080650329589844) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 1;
                } else {
                    if (features[133] <= 15.852378845214844) {
                        classes[0] = 0;
                        classes[1] = 50;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 2;
                    }
                }
            }
        } else {
            classes[0] = 31;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_42(double[] features) {
        int[] classes = new int[3];

        if (features[296] <= 9.51910400390625) {
            if (features[192] <= -11.745346069335938) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 20;
            } else {
                if (features[295] <= -6.102973937988281) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 5;
                } else {
                    if (features[123] <= 7.331245422363281) {
                        classes[0] = 0;
                        classes[1] = 35;
                        classes[2] = 0;
                    } else {
                        if (features[261] <= -2.45654296875) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 2;
                        } else {
                            classes[0] = 0;
                            classes[1] = 15;
                            classes[2] = 0;
                        }
                    }
                }
            }
        } else {
            if (features[288] <= 9.787788391113281) {
                classes[0] = 24;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_43(double[] features) {
        int[] classes = new int[3];

        if (features[178] <= -1.9575576782226562) {
            if (features[289] <= -3.3009796142578125) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 23;
            } else {
                if (features[104] <= 18.846290588378906) {
                    if (features[147] <= 32.088592529296875) {
                        if (features[224] <= 37.654197692871094) {
                            classes[0] = 0;
                            classes[1] = 38;
                            classes[2] = 0;
                        } else {
                            if (features[201] <= 8.751434326171875) {
                                classes[0] = 0;
                                classes[1] = 0;
                                classes[2] = 1;
                            } else {
                                classes[0] = 0;
                                classes[1] = 2;
                                classes[2] = 0;
                            }
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                } else {
                    if (features[138] <= -11.131210327148438) {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 9;
                    }
                }
            }
        } else {
            if (features[52] <= 0.7292861938476562) {
                classes[0] = 22;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 4;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_44(double[] features) {
        int[] classes = new int[3];

        if (features[266] <= 9.979705810546875) {
            if (features[130] <= 14.317039489746094) {
                if (features[198] <= -17.46448516845703) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 13;
                } else {
                    classes[0] = 0;
                    classes[1] = 42;
                    classes[2] = 0;
                }
            } else {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 16;
            }
        } else {
            classes[0] = 31;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_45(double[] features) {
        int[] classes = new int[3];

        if (features[272] <= 9.711021423339844) {
            if (features[145] <= 9.557487487792969) {
                if (features[296] <= 1.6504898071289062) {
                    if (features[251] <= 18.38568878173828) {
                        classes[0] = 0;
                        classes[1] = 35;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                } else {
                    if (features[95] <= 11.016059875488281) {
                        classes[0] = 0;
                        classes[1] = 5;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 10;
                    }
                }
            } else {
                if (features[28] <= 3.9151153564453125) {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 19;
                }
            }
        } else {
            if (features[291] <= 1.4969558715820312) {
                classes[0] = 30;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 1;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_46(double[] features) {
        int[] classes = new int[3];

        if (features[271] <= 0.2303009033203125) {
            if (features[122] <= 8.521133422851562) {
                if (features[189] <= -9.826171875) {
                    if (features[86] <= 11.6685791015625) {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 5;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 39;
                    classes[2] = 0;
                }
            } else {
                if (features[110] <= 35.735023498535156) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 30;
                } else {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                }
            }
        } else {
            if (features[163] <= -21.072532653808594) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 25;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_47(double[] features) {
        int[] classes = new int[3];

        if (features[60] <= 0.19191741943359375) {
            classes[0] = 34;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[274] <= -0.8060531616210938) {
                if (features[201] <= -7.254478454589844) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 23;
                } else {
                    if (features[228] <= -15.66046142578125) {
                        classes[0] = 0;
                        classes[1] = 12;
                        classes[2] = 0;
                    } else {
                        if (features[166] <= -25.563400268554688) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 5;
                        } else {
                            classes[0] = 0;
                            classes[1] = 1;
                            classes[2] = 0;
                        }
                    }
                }
            } else {
                classes[0] = 0;
                classes[1] = 27;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_48(double[] features) {
        int[] classes = new int[3];

        if (features[199] <= -5.6039886474609375) {
            if (features[136] <= 16.5816650390625) {
                if (features[88] <= -1.4585723876953125) {
                    if (features[155] <= 0.03838348388671875) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 5;
                    } else {
                        classes[0] = 0;
                        classes[1] = 3;
                        classes[2] = 0;
                    }
                } else {
                    if (features[280] <= -3.1090621948242188) {
                        if (features[122] <= -4.414100646972656) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 2;
                        } else {
                            classes[0] = 0;
                            classes[1] = 3;
                            classes[2] = 0;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 41;
                        classes[2] = 0;
                    }
                }
            } else {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 17;
            }
        } else {
            if (features[29] <= 7.5999298095703125) {
                classes[0] = 0;
                classes[1] = 4;
                classes[2] = 0;
            } else {
                classes[0] = 27;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_49(double[] features) {
        int[] classes = new int[3];

        if (features[241] <= -11.783729553222656) {
            if (features[289] <= -0.9595870971679688) {
                if (features[113] <= 14.470573425292969) {
                    if (features[261] <= -0.767669677734375) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 8;
                    } else {
                        classes[0] = 0;
                        classes[1] = 6;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 25;
                }
            } else {
                if (features[98] <= 19.61396026611328) {
                    classes[0] = 0;
                    classes[1] = 36;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 1;
                }
            }
        } else {
            if (features[148] <= 0.7292861938476562) {
                classes[0] = 25;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_50(double[] features) {
        int[] classes = new int[3];

        if (features[245] <= -2.2646255493164062) {
            if (features[204] <= -2.8403778076171875) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 13;
            } else {
                if (features[294] <= 0.42221832275390625) {
                    if (features[259] <= -6.410041809082031) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 3;
                    } else {
                        if (features[276] <= -2.76361083984375) {
                            if (features[259] <= 3.1474456787109375) {
                                classes[0] = 0;
                                classes[1] = 2;
                                classes[2] = 0;
                            } else {
                                classes[0] = 0;
                                classes[1] = 0;
                                classes[2] = 1;
                            }
                        } else {
                            classes[0] = 0;
                            classes[1] = 38;
                            classes[2] = 0;
                        }
                    }
                } else {
                    if (features[170] <= 24.21997833251953) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 8;
                    } else {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    }
                }
            }
        } else {
            classes[0] = 36;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_51(double[] features) {
        int[] classes = new int[3];

        if (features[22] <= 0.767669677734375) {
            if (features[277] <= -5.2969207763671875) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 26;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[295] <= -0.26868438720703125) {
                if (features[195] <= -6.179740905761719) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 31;
                } else {
                    if (features[288] <= -1.995941162109375) {
                        if (features[3] <= 6.870643615722656) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 4;
                        } else {
                            classes[0] = 0;
                            classes[1] = 1;
                            classes[2] = 0;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 6;
                        classes[2] = 0;
                    }
                }
            } else {
                if (features[157] <= -10.210006713867188) {
                    classes[0] = 0;
                    classes[1] = 31;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 2;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_52(double[] features) {
        int[] classes = new int[3];

        if (features[296] <= 9.672637939453125) {
            if (features[289] <= -0.9595870971679688) {
                if (features[92] <= 9.826171875) {
                    classes[0] = 0;
                    classes[1] = 3;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 31;
                }
            } else {
                if (features[49] <= 3.99188232421875) {
                    if (features[292] <= -0.153533935546875) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 2;
                    } else {
                        classes[0] = 0;
                        classes[1] = 5;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 31;
                    classes[2] = 0;
                }
            }
        } else {
            classes[0] = 30;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_53(double[] features) {
        int[] classes = new int[3];

        if (features[4] <= 0.7292861938476562) {
            classes[0] = 31;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[280] <= -3.0322952270507812) {
                if (features[56] <= 20.727081298828125) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 22;
                } else {
                    classes[0] = 0;
                    classes[1] = 2;
                    classes[2] = 0;
                }
            } else {
                if (features[153] <= 23.222007751464844) {
                    if (features[253] <= 35.92694091796875) {
                        classes[0] = 0;
                        classes[1] = 44;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 2;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_54(double[] features) {
        int[] classes = new int[3];

        if (features[1] <= 0.7292861938476562) {
            classes[0] = 35;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[198] <= -12.244331359863281) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 18;
            } else {
                if (features[98] <= 21.18768310546875) {
                    classes[0] = 0;
                    classes[1] = 43;
                    classes[2] = 0;
                } else {
                    if (features[221] <= 0.34545135498046875) {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 5;
                    }
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_55(double[] features) {
        int[] classes = new int[3];

        if (features[4] <= 0.7292861938476562) {
            classes[0] = 35;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[289] <= -3.3393630981445312) {
                if (features[190] <= -38.805702209472656) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 25;
                } else {
                    classes[0] = 0;
                    classes[1] = 2;
                    classes[2] = 0;
                }
            } else {
                if (features[97] <= -0.34545135498046875) {
                    if (features[169] <= -22.45433807373047) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 7;
                    } else {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    }
                } else {
                    if (features[44] <= 23.68260955810547) {
                        classes[0] = 0;
                        classes[1] = 29;
                        classes[2] = 0;
                    } else {
                        if (features[147] <= 10.478691101074219) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        } else {
                            classes[0] = 0;
                            classes[1] = 1;
                            classes[2] = 0;
                        }
                    }
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_56(double[] features) {
        int[] classes = new int[3];

        if (features[22] <= 0.7292861938476562) {
            if (features[132] <= -6.6787261962890625) {
                classes[0] = 0;
                classes[1] = 2;
                classes[2] = 0;
            } else {
                classes[0] = 39;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[280] <= -2.9939117431640625) {
                if (features[31] <= 6.486808776855469) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 23;
                } else {
                    if (features[225] <= -22.838172912597656) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    } else {
                        classes[0] = 0;
                        classes[1] = 4;
                        classes[2] = 0;
                    }
                }
            } else {
                if (features[156] <= 19.844261169433594) {
                    classes[0] = 0;
                    classes[1] = 31;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 2;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_57(double[] features) {
        int[] classes = new int[3];

        if (features[163] <= -7.67669677734375) {
            if (features[283] <= -2.9939117431640625) {
                if (features[43] <= 5.5272216796875) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 19;
                } else {
                    if (features[29] <= 7.9069976806640625) {
                        classes[0] = 0;
                        classes[1] = 3;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 2;
                    }
                }
            } else {
                if (features[136] <= 15.66046142578125) {
                    classes[0] = 0;
                    classes[1] = 40;
                    classes[2] = 0;
                } else {
                    if (features[161] <= -10.862525939941406) {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 3;
                    }
                }
            }
        } else {
            if (features[20] <= 8.329216003417969) {
                classes[0] = 0;
                classes[1] = 4;
                classes[2] = 0;
            } else {
                classes[0] = 30;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_58(double[] features) {
        int[] classes = new int[3];

        if (features[1] <= 0.8444366455078125) {
            classes[0] = 24;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[94] <= -0.767669677734375) {
                if (features[35] <= 4.874702453613281) {
                    classes[0] = 0;
                    classes[1] = 3;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 21;
                }
            } else {
                if (features[298] <= -3.7999649047851562) {
                    if (features[173] <= 13.395835876464844) {
                        classes[0] = 0;
                        classes[1] = 4;
                        classes[2] = 0;
                    } else {
                        if (features[269] <= 19.191741943359375) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 10;
                        } else {
                            classes[0] = 0;
                            classes[1] = 1;
                            classes[2] = 0;
                        }
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 39;
                    classes[2] = 0;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_59(double[] features) {
        int[] classes = new int[3];

        if (features[230] <= -0.153533935546875) {
            if (features[201] <= -7.254478454589844) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 27;
            } else {
                if (features[119] <= 13.472602844238281) {
                    if (features[228] <= -24.757347106933594) {
                        if (features[176] <= -0.0767669677734375) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 2;
                        } else {
                            classes[0] = 0;
                            classes[1] = 4;
                            classes[2] = 0;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 39;
                        classes[2] = 0;
                    }
                } else {
                    if (features[11] <= 6.5251922607421875) {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 3;
                    }
                }
            }
        } else {
            if (features[25] <= 3.3393630981445312) {
                classes[0] = 25;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_60(double[] features) {
        int[] classes = new int[3];

        if (features[54] <= -0.11515045166015625) {
            classes[0] = 29;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[195] <= -11.207977294921875) {
                if (features[32] <= 20.2664794921875) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 25;
                } else {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                }
            } else {
                if (features[136] <= 16.88873291015625) {
                    classes[0] = 0;
                    classes[1] = 44;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 3;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_61(double[] features) {
        int[] classes = new int[3];

        if (features[271] <= 0.2303009033203125) {
            if (features[201] <= -7.254478454589844) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 24;
            } else {
                if (features[274] <= -3.4545135498046875) {
                    if (features[276] <= -1.5737228393554688) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 6;
                    } else {
                        classes[0] = 0;
                        classes[1] = 4;
                        classes[2] = 0;
                    }
                } else {
                    if (features[223] <= -29.670433044433594) {
                        classes[0] = 0;
                        classes[1] = 26;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                }
            }
        } else {
            if (features[4] <= 2.3413925170898438) {
                classes[0] = 31;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 10;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_62(double[] features) {
        int[] classes = new int[3];

        if (features[10] <= 0.7292861938476562) {
            if (features[115] <= -0.92120361328125) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 22;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[283] <= -0.460601806640625) {
                if (features[62] <= 17.77155303955078) {
                    if (features[136] <= 9.749404907226562) {
                        if (features[216] <= -7.9069976806640625) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 4;
                        } else {
                            classes[0] = 0;
                            classes[1] = 2;
                            classes[2] = 0;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 27;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 6;
                    classes[2] = 0;
                }
            } else {
                if (features[286] <= -0.6525192260742188) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 1;
                } else {
                    classes[0] = 0;
                    classes[1] = 39;
                    classes[2] = 0;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_63(double[] features) {
        int[] classes = new int[3];

        if (features[245] <= -3.6080474853515625) {
            if (features[119] <= 12.397865295410156) {
                if (features[198] <= -17.19580078125) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 5;
                } else {
                    if (features[280] <= -14.547340393066406) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 2;
                    } else {
                        if (features[152] <= 10.248390197753906) {
                            classes[0] = 0;
                            classes[1] = 40;
                            classes[2] = 0;
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        }
                    }
                }
            } else {
                if (features[160] <= 4.759552001953125) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 23;
                } else {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                }
            }
        } else {
            classes[0] = 30;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_64(double[] features) {
        int[] classes = new int[3];

        if (features[136] <= 11.054443359375) {
            if (features[22] <= 0.7292861938476562) {
                if (features[46] <= -19.422042846679688) {
                    classes[0] = 0;
                    classes[1] = 2;
                    classes[2] = 0;
                } else {
                    classes[0] = 23;
                    classes[1] = 0;
                    classes[2] = 0;
                }
            } else {
                if (features[156] <= 19.882644653320312) {
                    classes[0] = 0;
                    classes[1] = 41;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 2;
                }
            }
        } else {
            if (features[40] <= 6.294891357421875) {
                if (features[100] <= 5.488838195800781) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 29;
                } else {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                }
            } else {
                classes[0] = 0;
                classes[1] = 4;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_65(double[] features) {
        int[] classes = new int[3];

        if (features[22] <= 0.7292861938476562) {
            if (features[253] <= -0.3838348388671875) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 30;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[295] <= -0.92120361328125) {
                if (features[156] <= 11.246360778808594) {
                    if (features[267] <= -15.66046142578125) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    } else {
                        classes[0] = 0;
                        classes[1] = 5;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 26;
                }
            } else {
                if (features[255] <= -17.8099365234375) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 1;
                } else {
                    classes[0] = 0;
                    classes[1] = 38;
                    classes[2] = 0;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_66(double[] features) {
        int[] classes = new int[3];

        if (features[248] <= 3.7999649047851562) {
            if (features[295] <= -1.995941162109375) {
                if (features[151] <= -23.64422607421875) {
                    classes[0] = 0;
                    classes[1] = 4;
                    classes[2] = 0;
                } else {
                    if (features[178] <= -12.321098327636719) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 31;
                    } else {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    }
                }
            } else {
                classes[0] = 0;
                classes[1] = 33;
                classes[2] = 0;
            }
        } else {
            if (features[169] <= -22.224037170410156) {
                if (features[10] <= 3.6464309692382812) {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 2;
                }
            } else {
                classes[0] = 30;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_67(double[] features) {
        int[] classes = new int[3];

        if (features[22] <= 2.0343246459960938) {
            if (features[148] <= -7.5999298095703125) {
                classes[0] = 0;
                classes[1] = 2;
                classes[2] = 0;
            } else {
                classes[0] = 33;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[113] <= 8.175682067871094) {
                if (features[232] <= -37.577430725097656) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 1;
                } else {
                    classes[0] = 0;
                    classes[1] = 31;
                    classes[2] = 0;
                }
            } else {
                if (features[286] <= -0.19191741943359375) {
                    if (features[41] <= 11.706962585449219) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 25;
                    } else {
                        classes[0] = 0;
                        classes[1] = 4;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 6;
                    classes[2] = 0;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_68(double[] features) {
        int[] classes = new int[3];

        if (features[81] <= 0.5757522583007812) {
            classes[0] = 33;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[294] <= 0.6525192260742188) {
                if (features[283] <= -1.3434219360351562) {
                    if (features[134] <= -0.19191741943359375) {
                        if (features[112] <= 8.022148132324219) {
                            classes[0] = 0;
                            classes[1] = 7;
                            classes[2] = 0;
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 3;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 5;
                    }
                } else {
                    if (features[110] <= 20.650314331054688) {
                        classes[0] = 0;
                        classes[1] = 33;
                        classes[2] = 0;
                    } else {
                        if (features[282] <= -1.074737548828125) {
                            classes[0] = 0;
                            classes[1] = 2;
                            classes[2] = 0;
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        }
                    }
                }
            } else {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 18;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_69(double[] features) {
        int[] classes = new int[3];

        if (features[281] <= 8.444366455078125) {
            if (features[119] <= 8.175682067871094) {
                if (features[186] <= -8.90496826171875) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 2;
                } else {
                    classes[0] = 0;
                    classes[1] = 33;
                    classes[2] = 0;
                }
            } else {
                if (features[40] <= 6.256507873535156) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 23;
                } else {
                    classes[0] = 0;
                    classes[1] = 3;
                    classes[2] = 0;
                }
            }
        } else {
            classes[0] = 41;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_70(double[] features) {
        int[] classes = new int[3];

        if (features[275] <= 8.597900390625) {
            if (features[142] <= 12.167564392089844) {
                if (features[279] <= 2.2646255493164062) {
                    if (features[267] <= -2.8403778076171875) {
                        if (features[135] <= 12.628166198730469) {
                            classes[0] = 0;
                            classes[1] = 2;
                            classes[2] = 0;
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 45;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 2;
                }
            } else {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 18;
            }
        } else {
            classes[0] = 34;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_71(double[] features) {
        int[] classes = new int[3];

        if (features[181] <= -7.830230712890625) {
            if (features[201] <= -7.638313293457031) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 27;
            } else {
                if (features[228] <= -15.161476135253906) {
                    if (features[119] <= -7.4463958740234375) {
                        if (features[128] <= -11.399894714355469) {
                            classes[0] = 0;
                            classes[1] = 2;
                            classes[2] = 0;
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 32;
                        classes[2] = 0;
                    }
                } else {
                    if (features[144] <= 6.9857940673828125) {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 5;
                    }
                }
            }
        } else {
            if (features[6] <= 3.0322952270507812) {
                classes[0] = 31;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 2;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_72(double[] features) {
        int[] classes = new int[3];

        if (features[169] <= -5.4504547119140625) {
            if (features[131] <= 1.6121063232421875) {
                if (features[286] <= -3.5696640014648438) {
                    if (features[102] <= 18.347305297851562) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 5;
                    } else {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 31;
                    classes[2] = 0;
                }
            } else {
                if (features[113] <= 34.66028594970703) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 24;
                } else {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                }
            }
        } else {
            if (features[196] <= 1.8424072265625) {
                classes[0] = 38;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 2;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_73(double[] features) {
        int[] classes = new int[3];

        if (features[16] <= 1.9191741943359375) {
            if (features[55] <= -19.422042846679688) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 35;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[198] <= -14.969558715820312) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 25;
            } else {
                if (features[28] <= 4.337333679199219) {
                    if (features[80] <= 21.14929962158203) {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 3;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 37;
                    classes[2] = 0;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_74(double[] features) {
        int[] classes = new int[3];

        if (features[25] <= 0.767669677734375) {
            classes[0] = 29;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[156] <= 20.688697814941406) {
                if (features[113] <= 13.933204650878906) {
                    if (features[229] <= -39.189537048339844) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    } else {
                        classes[0] = 0;
                        classes[1] = 53;
                        classes[2] = 0;
                    }
                } else {
                    if (features[55] <= 7.484779357910156) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 5;
                    } else {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    }
                }
            } else {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 13;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_75(double[] features) {
        int[] classes = new int[3];

        if (features[146] <= 9.480720520019531) {
            if (features[113] <= 12.704933166503906) {
                if (features[283] <= -2.8403778076171875) {
                    if (features[76] <= -4.145416259765625) {
                        classes[0] = 0;
                        classes[1] = 3;
                        classes[2] = 0;
                    } else {
                        if (features[76] <= 8.444366455078125) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 4;
                        } else {
                            classes[0] = 0;
                            classes[1] = 1;
                            classes[2] = 0;
                        }
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 34;
                    classes[2] = 0;
                }
            } else {
                if (features[77] <= 27.252273559570312) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 27;
                } else {
                    classes[0] = 0;
                    classes[1] = 2;
                    classes[2] = 0;
                }
            }
        } else {
            classes[0] = 31;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_76(double[] features) {
        int[] classes = new int[3];

        if (features[245] <= -2.2646255493164062) {
            if (features[156] <= 15.545310974121094) {
                if (features[274] <= -6.141357421875) {
                    if (features[262] <= -8.022148132324219) {
                        classes[0] = 0;
                        classes[1] = 5;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 5;
                    }
                } else {
                    if (features[124] <= 13.280685424804688) {
                        classes[0] = 0;
                        classes[1] = 36;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                }
            } else {
                if (features[195] <= -4.644401550292969) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 23;
                } else {
                    classes[0] = 0;
                    classes[1] = 3;
                    classes[2] = 0;
                }
            }
        } else {
            if (features[260] <= -0.5757522583007812) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 1;
            } else {
                classes[0] = 28;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_77(double[] features) {
        int[] classes = new int[3];

        if (features[28] <= 0.7292861938476562) {
            if (features[260] <= 5.795906066894531) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 32;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[148] <= 0.8828201293945312) {
                if (features[192] <= -18.923057556152344) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 8;
                } else {
                    if (features[295] <= -1.995941162109375) {
                        if (features[141] <= 14.1251220703125) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 3;
                        } else {
                            classes[0] = 0;
                            classes[1] = 4;
                            classes[2] = 0;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 42;
                        classes[2] = 0;
                    }
                }
            } else {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 12;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_78(double[] features) {
        int[] classes = new int[3];

        if (features[281] <= 8.29083251953125) {
            if (features[201] <= -8.444366455078125) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 24;
            } else {
                if (features[126] <= 20.074562072753906) {
                    if (features[262] <= -13.626136779785156) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    } else {
                        classes[0] = 0;
                        classes[1] = 40;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 4;
                }
            }
        } else {
            classes[0] = 33;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_79(double[] features) {
        int[] classes = new int[3];

        if (features[31] <= 0.6909027099609375) {
            if (features[58] <= -1.4201889038085938) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 32;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[192] <= -6.9857940673828125) {
                if (features[128] <= 1.3050384521484375) {
                    if (features[236] <= -3.4161300659179688) {
                        classes[0] = 0;
                        classes[1] = 4;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 2;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 23;
                }
            } else {
                if (features[106] <= 7.024177551269531) {
                    if (features[133] <= 17.50286865234375) {
                        classes[0] = 0;
                        classes[1] = 36;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                } else {
                    if (features[192] <= -0.0767669677734375) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 2;
                    } else {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    }
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_80(double[] features) {
        int[] classes = new int[3];

        if (features[25] <= 0.767669677734375) {
            if (features[228] <= -8.482749938964844) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 27;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[153] <= 18.38568878173828) {
                if (features[98] <= 19.460426330566406) {
                    if (features[252] <= 9.403953552246094) {
                        classes[0] = 0;
                        classes[1] = 37;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                } else {
                    if (features[120] <= 23.91291046142578) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 4;
                    } else {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    }
                }
            } else {
                if (features[40] <= 6.2181243896484375) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 27;
                } else {
                    classes[0] = 0;
                    classes[1] = 3;
                    classes[2] = 0;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_81(double[] features) {
        int[] classes = new int[3];

        if (features[16] <= 0.767669677734375) {
            if (features[142] <= -6.294891357421875) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 20;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[195] <= -12.28271484375) {
                if (features[8] <= 11.97564697265625) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 26;
                } else {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                }
            } else {
                if (features[277] <= -3.37774658203125) {
                    if (features[4] <= 5.642372131347656) {
                        classes[0] = 0;
                        classes[1] = 4;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 5;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 45;
                    classes[2] = 0;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_82(double[] features) {
        int[] classes = new int[3];

        if (features[37] <= 0.767669677734375) {
            if (features[244] <= -26.292686462402344) {
                if (features[0] <= 5.719139099121094) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 2;
                } else {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                }
            } else {
                classes[0] = 26;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[195] <= -6.179740905761719) {
                if (features[62] <= 16.773582458496094) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 21;
                } else {
                    classes[0] = 0;
                    classes[1] = 3;
                    classes[2] = 0;
                }
            } else {
                if (features[264] <= -2.6484603881835938) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 4;
                } else {
                    classes[0] = 0;
                    classes[1] = 45;
                    classes[2] = 0;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_83(double[] features) {
        int[] classes = new int[3];

        if (features[57] <= -0.03838348388671875) {
            if (features[116] <= 8.981735229492188) {
                classes[0] = 0;
                classes[1] = 3;
                classes[2] = 0;
            } else {
                classes[0] = 28;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[198] <= -9.634254455566406) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 32;
            } else {
                if (features[149] <= 7.408012390136719) {
                    classes[0] = 0;
                    classes[1] = 36;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 3;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_84(double[] features) {
        int[] classes = new int[3];

        if (features[63] <= 0.8828201293945312) {
            if (features[16] <= 3.2625961303710938) {
                classes[0] = 31;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 2;
                classes[2] = 0;
            }
        } else {
            if (features[277] <= -2.7252273559570312) {
                if (features[25] <= 8.521133422851562) {
                    if (features[274] <= -3.4545135498046875) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 27;
                    } else {
                        if (features[32] <= 7.868614196777344) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 3;
                        } else {
                            classes[0] = 0;
                            classes[1] = 1;
                            classes[2] = 0;
                        }
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 2;
                    classes[2] = 0;
                }
            } else {
                if (features[109] <= 2.917144775390625) {
                    if (features[129] <= 23.98967742919922) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 3;
                    } else {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 32;
                    classes[2] = 0;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_85(double[] features) {
        int[] classes = new int[3];

        if (features[22] <= 0.767669677734375) {
            if (features[208] <= -6.256507873535156) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 29;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[81] <= 8.367599487304688) {
                if (features[249] <= 12.973617553710938) {
                    classes[0] = 0;
                    classes[1] = 27;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 1;
                }
            } else {
                if (features[116] <= 8.405982971191406) {
                    if (features[285] <= -11.937263488769531) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 2;
                    } else {
                        classes[0] = 0;
                        classes[1] = 13;
                        classes[2] = 0;
                    }
                } else {
                    if (features[97] <= 7.9837646484375) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 28;
                    } else {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    }
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_86(double[] features) {
        int[] classes = new int[3];

        if (features[290] <= 8.789817810058594) {
            if (features[133] <= 16.351364135742188) {
                if (features[154] <= -10.018089294433594) {
                    if (features[270] <= -1.688873291015625) {
                        if (features[60] <= 14.201889038085938) {
                            if (features[224] <= 30.207801818847656) {
                                classes[0] = 0;
                                classes[1] = 4;
                                classes[2] = 0;
                            } else {
                                classes[0] = 0;
                                classes[1] = 0;
                                classes[2] = 1;
                            }
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 3;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 38;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 10;
                }
            } else {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 21;
            }
        } else {
            classes[0] = 25;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_87(double[] features) {
        int[] classes = new int[3];

        if (features[236] <= -1.8040237426757812) {
            if (features[286] <= -0.5757522583007812) {
                if (features[260] <= -1.1515045166015625) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 28;
                } else {
                    if (features[40] <= 4.874702453613281) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    } else {
                        classes[0] = 0;
                        classes[1] = 6;
                        classes[2] = 0;
                    }
                }
            } else {
                if (features[51] <= 1.1131210327148438) {
                    if (features[275] <= -0.153533935546875) {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 38;
                    classes[2] = 0;
                }
            }
        } else {
            if (features[279] <= -14.009971618652344) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 2;
            } else {
                classes[0] = 24;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_88(double[] features) {
        int[] classes = new int[3];

        if (features[284] <= 7.830230712890625) {
            if (features[140] <= 0.7292861938476562) {
                if (features[283] <= -2.610076904296875) {
                    if (features[133] <= 9.902938842773438) {
                        classes[0] = 0;
                        classes[1] = 3;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 10;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 35;
                    classes[2] = 0;
                }
            } else {
                if (features[260] <= 2.0343246459960938) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 22;
                } else {
                    classes[0] = 0;
                    classes[1] = 2;
                    classes[2] = 0;
                }
            }
        } else {
            classes[0] = 30;
            classes[1] = 0;
            classes[2] = 0;
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_89(double[] features) {
        int[] classes = new int[3];

        if (features[2] <= 9.749404907226562) {
            if (features[134] <= 4.260566711425781) {
                if (features[299] <= 3.3393630981445312) {
                    if (features[230] <= -3.6080474853515625) {
                        if (features[52] <= 8.329216003417969) {
                            if (features[254] <= -27.866409301757812) {
                                classes[0] = 0;
                                classes[1] = 0;
                                classes[2] = 1;
                            } else {
                                classes[0] = 0;
                                classes[1] = 9;
                                classes[2] = 0;
                            }
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 2;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 32;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 4;
                }
            } else {
                if (features[279] <= 9.672637939453125) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 22;
                } else {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                }
            }
        } else {
            if (features[293] <= 5.719139099121094) {
                classes[0] = 0;
                classes[1] = 4;
                classes[2] = 0;
            } else {
                classes[0] = 27;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_90(double[] features) {
        int[] classes = new int[3];

        if (features[245] <= 0.03838348388671875) {
            if (features[201] <= -7.715080261230469) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 24;
            } else {
                if (features[126] <= 19.65234375) {
                    classes[0] = 0;
                    classes[1] = 43;
                    classes[2] = 0;
                } else {
                    if (features[286] <= -5.719139099121094) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 3;
                    } else {
                        classes[0] = 0;
                        classes[1] = 4;
                        classes[2] = 0;
                    }
                }
            }
        } else {
            if (features[289] <= -1.2666549682617188) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 1;
            } else {
                classes[0] = 27;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_91(double[] features) {
        int[] classes = new int[3];

        if (features[1] <= 0.6909027099609375) {
            if (features[264] <= 11.476661682128906) {
                classes[0] = 25;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            }
        } else {
            if (features[289] <= -0.7292861938476562) {
                if (features[136] <= 10.939292907714844) {
                    if (features[270] <= -16.658432006835938) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 1;
                    } else {
                        if (features[207] <= -7.484779357910156) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        } else {
                            classes[0] = 0;
                            classes[1] = 13;
                            classes[2] = 0;
                        }
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 24;
                }
            } else {
                classes[0] = 0;
                classes[1] = 37;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_92(double[] features) {
        int[] classes = new int[3];

        if (features[254] <= 5.987823486328125) {
            if (features[148] <= 0.8828201293945312) {
                if (features[139] <= 14.585723876953125) {
                    classes[0] = 0;
                    classes[1] = 40;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 4;
                }
            } else {
                if (features[253] <= 8.022148132324219) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 27;
                } else {
                    if (features[96] <= 15.04632568359375) {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 3;
                    }
                }
            }
        } else {
            if (features[53] <= 9.2120361328125) {
                if (features[88] <= 0.30706787109375) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 2;
                } else {
                    classes[0] = 0;
                    classes[1] = 1;
                    classes[2] = 0;
                }
            } else {
                classes[0] = 23;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_93(double[] features) {
        int[] classes = new int[3];

        if (features[40] <= 0.8828201293945312) {
            if (features[112] <= -1.7272567749023438) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 25;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[198] <= -15.506927490234375) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 29;
            } else {
                if (features[115] <= 13.319068908691406) {
                    classes[0] = 0;
                    classes[1] = 44;
                    classes[2] = 0;
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 3;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_94(double[] features) {
        int[] classes = new int[3];

        if (features[28] <= 1.4201889038085938) {
            if (features[291] <= -1.3050384521484375) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 30;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[192] <= -0.0767669677734375) {
                if (features[122] <= 6.947410583496094) {
                    if (features[274] <= -3.4545135498046875) {
                        if (features[259] <= 5.6039886474609375) {
                            if (features[258] <= 4.60601806640625) {
                                classes[0] = 0;
                                classes[1] = 0;
                                classes[2] = 8;
                            } else {
                                classes[0] = 0;
                                classes[1] = 1;
                                classes[2] = 0;
                            }
                        } else {
                            classes[0] = 0;
                            classes[1] = 3;
                            classes[2] = 0;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 11;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 20;
                }
            } else {
                classes[0] = 0;
                classes[1] = 28;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_95(double[] features) {
        int[] classes = new int[3];

        if (features[169] <= -16.082679748535156) {
            if (features[259] <= -5.7575225830078125) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 19;
            } else {
                if (features[277] <= -2.7252273559570312) {
                    if (features[139] <= 5.4504547119140625) {
                        if (features[97] <= 4.2989501953125) {
                            classes[0] = 0;
                            classes[1] = 3;
                            classes[2] = 0;
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 8;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 34;
                    classes[2] = 0;
                }
            }
        } else {
            if (features[176] <= -7.1393280029296875) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 36;
                classes[1] = 0;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_96(double[] features) {
        int[] classes = new int[3];

        if (features[290] <= 8.789817810058594) {
            if (features[201] <= -7.638313293457031) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 20;
            } else {
                if (features[186] <= -13.971588134765625) {
                    if (features[67] <= 3.37774658203125) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 4;
                    } else {
                        classes[0] = 0;
                        classes[1] = 1;
                        classes[2] = 0;
                    }
                } else {
                    if (features[163] <= -27.02197265625) {
                        classes[0] = 0;
                        classes[1] = 41;
                        classes[2] = 0;
                    } else {
                        if (features[287] <= 1.1515045166015625) {
                            classes[0] = 0;
                            classes[1] = 3;
                            classes[2] = 0;
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 1;
                        }
                    }
                }
            }
        } else {
            if (features[278] <= 24.795730590820312) {
                classes[0] = 31;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_97(double[] features) {
        int[] classes = new int[3];

        if (features[13] <= 0.8444366455078125) {
            classes[0] = 20;
            classes[1] = 0;
            classes[2] = 0;
        } else {
            if (features[79] <= 2.0727081298828125) {
                if (features[289] <= 0.19191741943359375) {
                    if (features[95] <= 8.828201293945312) {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    } else {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 26;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 6;
                    classes[2] = 0;
                }
            } else {
                if (features[137] <= 12.436248779296875) {
                    if (features[161] <= -4.452484130859375) {
                        if (features[280] <= -1.6121063232421875) {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 2;
                        } else {
                            classes[0] = 0;
                            classes[1] = 7;
                            classes[2] = 0;
                        }
                    } else {
                        classes[0] = 0;
                        classes[1] = 38;
                        classes[2] = 0;
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 1;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_98(double[] features) {
        int[] classes = new int[3];

        if (features[31] <= 0.6909027099609375) {
            if (features[10] <= -19.0382080078125) {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            } else {
                classes[0] = 35;
                classes[1] = 0;
                classes[2] = 0;
            }
        } else {
            if (features[192] <= -18.923057556152344) {
                classes[0] = 0;
                classes[1] = 0;
                classes[2] = 10;
            } else {
                if (features[113] <= 20.074562072753906) {
                    if (features[254] <= 2.14947509765625) {
                        classes[0] = 0;
                        classes[1] = 46;
                        classes[2] = 0;
                    } else {
                        if (features[6] <= -0.8060531616210938) {
                            classes[0] = 0;
                            classes[1] = 2;
                            classes[2] = 0;
                        } else {
                            classes[0] = 0;
                            classes[1] = 0;
                            classes[2] = 3;
                        }
                    }
                } else {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 5;
                }
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict_99(double[] features) {
        int[] classes = new int[3];

        if (features[278] <= 8.866584777832031) {
            if (features[274] <= -2.3413925170898438) {
                if (features[33] <= 4.759552001953125) {
                    classes[0] = 0;
                    classes[1] = 0;
                    classes[2] = 23;
                } else {
                    if (features[131] <= -13.395835876464844) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 3;
                    } else {
                        classes[0] = 0;
                        classes[1] = 5;
                        classes[2] = 0;
                    }
                }
            } else {
                if (features[107] <= 15.929145812988281) {
                    classes[0] = 0;
                    classes[1] = 38;
                    classes[2] = 0;
                } else {
                    if (features[211] <= -19.844261169433594) {
                        classes[0] = 0;
                        classes[1] = 0;
                        classes[2] = 2;
                    } else {
                        classes[0] = 0;
                        classes[1] = 2;
                        classes[2] = 0;
                    }
                }
            }
        } else {
            if (features[1] <= 3.224212646484375) {
                classes[0] = 28;
                classes[1] = 0;
                classes[2] = 0;
            } else {
                classes[0] = 0;
                classes[1] = 1;
                classes[2] = 0;
            }
        }
        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < 3; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static int predict(double[] features) {
        int n_classes = 3;
        int[] classes = new int[n_classes];
        classes[RFClassifierForImpactData.predict_0(features)]++;
        classes[RFClassifierForImpactData.predict_1(features)]++;
        classes[RFClassifierForImpactData.predict_2(features)]++;
        classes[RFClassifierForImpactData.predict_3(features)]++;
        classes[RFClassifierForImpactData.predict_4(features)]++;
        classes[RFClassifierForImpactData.predict_5(features)]++;
        classes[RFClassifierForImpactData.predict_6(features)]++;
        classes[RFClassifierForImpactData.predict_7(features)]++;
        classes[RFClassifierForImpactData.predict_8(features)]++;
        classes[RFClassifierForImpactData.predict_9(features)]++;
        classes[RFClassifierForImpactData.predict_10(features)]++;
        classes[RFClassifierForImpactData.predict_11(features)]++;
        classes[RFClassifierForImpactData.predict_12(features)]++;
        classes[RFClassifierForImpactData.predict_13(features)]++;
        classes[RFClassifierForImpactData.predict_14(features)]++;
        classes[RFClassifierForImpactData.predict_15(features)]++;
        classes[RFClassifierForImpactData.predict_16(features)]++;
        classes[RFClassifierForImpactData.predict_17(features)]++;
        classes[RFClassifierForImpactData.predict_18(features)]++;
        classes[RFClassifierForImpactData.predict_19(features)]++;
        classes[RFClassifierForImpactData.predict_20(features)]++;
        classes[RFClassifierForImpactData.predict_21(features)]++;
        classes[RFClassifierForImpactData.predict_22(features)]++;
        classes[RFClassifierForImpactData.predict_23(features)]++;
        classes[RFClassifierForImpactData.predict_24(features)]++;
        classes[RFClassifierForImpactData.predict_25(features)]++;
        classes[RFClassifierForImpactData.predict_26(features)]++;
        classes[RFClassifierForImpactData.predict_27(features)]++;
        classes[RFClassifierForImpactData.predict_28(features)]++;
        classes[RFClassifierForImpactData.predict_29(features)]++;
        classes[RFClassifierForImpactData.predict_30(features)]++;
        classes[RFClassifierForImpactData.predict_31(features)]++;
        classes[RFClassifierForImpactData.predict_32(features)]++;
        classes[RFClassifierForImpactData.predict_33(features)]++;
        classes[RFClassifierForImpactData.predict_34(features)]++;
        classes[RFClassifierForImpactData.predict_35(features)]++;
        classes[RFClassifierForImpactData.predict_36(features)]++;
        classes[RFClassifierForImpactData.predict_37(features)]++;
        classes[RFClassifierForImpactData.predict_38(features)]++;
        classes[RFClassifierForImpactData.predict_39(features)]++;
        classes[RFClassifierForImpactData.predict_40(features)]++;
        classes[RFClassifierForImpactData.predict_41(features)]++;
        classes[RFClassifierForImpactData.predict_42(features)]++;
        classes[RFClassifierForImpactData.predict_43(features)]++;
        classes[RFClassifierForImpactData.predict_44(features)]++;
        classes[RFClassifierForImpactData.predict_45(features)]++;
        classes[RFClassifierForImpactData.predict_46(features)]++;
        classes[RFClassifierForImpactData.predict_47(features)]++;
        classes[RFClassifierForImpactData.predict_48(features)]++;
        classes[RFClassifierForImpactData.predict_49(features)]++;
        classes[RFClassifierForImpactData.predict_50(features)]++;
        classes[RFClassifierForImpactData.predict_51(features)]++;
        classes[RFClassifierForImpactData.predict_52(features)]++;
        classes[RFClassifierForImpactData.predict_53(features)]++;
        classes[RFClassifierForImpactData.predict_54(features)]++;
        classes[RFClassifierForImpactData.predict_55(features)]++;
        classes[RFClassifierForImpactData.predict_56(features)]++;
        classes[RFClassifierForImpactData.predict_57(features)]++;
        classes[RFClassifierForImpactData.predict_58(features)]++;
        classes[RFClassifierForImpactData.predict_59(features)]++;
        classes[RFClassifierForImpactData.predict_60(features)]++;
        classes[RFClassifierForImpactData.predict_61(features)]++;
        classes[RFClassifierForImpactData.predict_62(features)]++;
        classes[RFClassifierForImpactData.predict_63(features)]++;
        classes[RFClassifierForImpactData.predict_64(features)]++;
        classes[RFClassifierForImpactData.predict_65(features)]++;
        classes[RFClassifierForImpactData.predict_66(features)]++;
        classes[RFClassifierForImpactData.predict_67(features)]++;
        classes[RFClassifierForImpactData.predict_68(features)]++;
        classes[RFClassifierForImpactData.predict_69(features)]++;
        classes[RFClassifierForImpactData.predict_70(features)]++;
        classes[RFClassifierForImpactData.predict_71(features)]++;
        classes[RFClassifierForImpactData.predict_72(features)]++;
        classes[RFClassifierForImpactData.predict_73(features)]++;
        classes[RFClassifierForImpactData.predict_74(features)]++;
        classes[RFClassifierForImpactData.predict_75(features)]++;
        classes[RFClassifierForImpactData.predict_76(features)]++;
        classes[RFClassifierForImpactData.predict_77(features)]++;
        classes[RFClassifierForImpactData.predict_78(features)]++;
        classes[RFClassifierForImpactData.predict_79(features)]++;
        classes[RFClassifierForImpactData.predict_80(features)]++;
        classes[RFClassifierForImpactData.predict_81(features)]++;
        classes[RFClassifierForImpactData.predict_82(features)]++;
        classes[RFClassifierForImpactData.predict_83(features)]++;
        classes[RFClassifierForImpactData.predict_84(features)]++;
        classes[RFClassifierForImpactData.predict_85(features)]++;
        classes[RFClassifierForImpactData.predict_86(features)]++;
        classes[RFClassifierForImpactData.predict_87(features)]++;
        classes[RFClassifierForImpactData.predict_88(features)]++;
        classes[RFClassifierForImpactData.predict_89(features)]++;
        classes[RFClassifierForImpactData.predict_90(features)]++;
        classes[RFClassifierForImpactData.predict_91(features)]++;
        classes[RFClassifierForImpactData.predict_92(features)]++;
        classes[RFClassifierForImpactData.predict_93(features)]++;
        classes[RFClassifierForImpactData.predict_94(features)]++;
        classes[RFClassifierForImpactData.predict_95(features)]++;
        classes[RFClassifierForImpactData.predict_96(features)]++;
        classes[RFClassifierForImpactData.predict_97(features)]++;
        classes[RFClassifierForImpactData.predict_98(features)]++;
        classes[RFClassifierForImpactData.predict_99(features)]++;

        int class_idx = 0;
        int class_val = classes[0];
        for (int i = 1; i < n_classes; i++) {
            if (classes[i] > class_val) {
                class_idx = i;
                class_val = classes[i];
            }
        }
        return class_idx;
    }

    public static void main(String[] args) {
        if (args.length == 300) {

            // Features:
            double[] features = new double[args.length];
            for (int i = 0, l = args.length; i < l; i++) {
                features[i] = Double.parseDouble(args[i]);
            }

            // Prediction:
            int prediction = RFClassifierForImpactData.predict(features);
            System.out.println(prediction);

        }
    }
}