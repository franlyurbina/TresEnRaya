package com.example.franly.tresenraya;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by franly on 07/02/2018.
 */

class Partida {

    private int dificultad;
    private int jugador;
    private ArrayList<Integer> arrayCasillas;
    private int[] combinaciones;


    public Partida(int dificultad) {
        this.dificultad = dificultad;
        jugador = 2;

        arrayCasillas = new ArrayList<>();
        combinaciones = new int[9];

        for (int i = 0; i < 9; i++) {
            arrayCasillas.add((Integer) i);
            combinaciones[i] = 0;
        }

    }


    public void addPosicion(int combinacion) {
        this.combinaciones[combinacion] = 1;
    }

    public int getJugador() {
        return jugador;
    }

    public ArrayList<Integer> getArrayListCasillas() {
        return arrayCasillas;
    }


    public void removeArrayListCasillas(int casilla) {

        for (int i = 0; i < arrayCasillas.size(); i++) {
            if (arrayCasillas.get(i) == casilla) {
                arrayCasillas.remove(i);
            }
        }
    }

    /**
     * Cambia el turno del jugador1 y jugador2
     */
    public void cambioDeTurno() {
        if (getJugador() == 1) {
            jugador = 2;
        } else {
            jugador = 1;
        }
    }

    /**
     * Inteligencia de la máquina para competir contra un jugador
     *
     * @return casilla en la que jugará la máquina
     */
    public int ia() {
        int casilla = 0;
        int randomIndex = 0;
        Random selection;
        ArrayList<Integer> opciones = new ArrayList<>();

        // Mientras queden casillas libres
        if ((arrayCasillas.size() > 0)) {

            if (dificultad == 0) {
                selection = new Random(arrayCasillas.size());

                // Elegimos un índice al azar, entre 0 y el número de casillas libres que quedan
                randomIndex = selection.nextInt(arrayCasillas.size());
                casilla = arrayCasillas.get(randomIndex);

            } else if (dificultad == 1 || dificultad == 2) {
                boolean conseguido = false;
                int cont = 0;
                int contX = 0;

                //* HORIZONTAL
                for (int i = 0; i < 9; i += 3) {
                    for (int j = i; j < 3 + i; j++) {
                        if (combinaciones[j] == 1) {
                            cont++;
                        }

                        if (combinaciones[j] == 2) {
                            contX++;
                        }
                    }

                    if (contX > cont) {
                        for (int j = i; j < 3 + i; j++) {
                            if (combinaciones[j] == 0) {
                                combinaciones[j] = 2;
                                casilla = j;
                                conseguido = true;
                                break;
                            }
                        }
                    } else  {
                        for (int j = i; j < 3 + i; j++) {
                            if (combinaciones[j] == 0) {
                                combinaciones[j] = 2;
                                casilla = j;
                                conseguido = true;
                                break;
                            }
                        }
                    }

                    contX = 0;
                    cont = 0;
                    if (conseguido) {
                        break;
                    }
                }

                //* VERTICAL
                if (!conseguido) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = i; j < 9; j += 3) {
                            if (combinaciones[j] == 1) {
                                cont++;
                            }
                            if (combinaciones[j] == 2) {
                                contX++;
                            }
                        }
                        if (contX > cont) {
                            for (int j = i; j < 9; j += 3) {
                                if (combinaciones[j] == 0) {
                                    combinaciones[j] = 2;
                                    casilla = j;
                                    conseguido = true;
                                    break;
                                }
                            }
                        } else   {
                            for (int j = i; j < 9; j += 3) {
                                if (combinaciones[j] == 0) {
                                    combinaciones[j] = 2;
                                    casilla = j;
                                    conseguido = true;
                                    break;
                                }
                            }
                        }
                        contX = 0;
                        cont = 0;
                        if (conseguido) {
                            break;
                        }
                    }
                }

                //** 0,4,8
                if (!conseguido) {
                    for (int j = 0; j < 9; j += 4) {
                        if (combinaciones[j] == 1) {
                            cont++;
                        }
                        if (combinaciones[j] == 2) {
                            contX++;
                        }
                    }
                    if (contX > cont) {
                        for (int j = 0; j < 9; j += 4) {
                            if (combinaciones[j] == 0) {
                                combinaciones[j] = 2;
                                casilla = j;
                                conseguido = true;
                                break;
                            }
                        }
                    } else   {
                        for (int j = 0; j < 9; j += 4) {
                            if (combinaciones[j] == 0) {
                                combinaciones[j] = 2;
                                casilla = j;
                                conseguido = true;
                                break;
                            }
                        }
                    }
                    contX = 0;
                    cont = 0;

                }

                //** 2,4,6
                if (!conseguido) {
                    for (int j = 2; j < 7; j += 2) {
                        if (combinaciones[j] == 1) {
                            cont++;
                        }
                        if (combinaciones[j] == 2) {
                            contX++;
                        }
                    }

                    if (contX > cont) {
                        for (int j = 2; j < 7; j += 2) {
                            if (combinaciones[j] == 0) {
                                combinaciones[j] = 2;
                                casilla = j;
                                conseguido = true;
                                break;
                            }
                        }
                    } else   {
                        for (int j = 2; j < 7; j += 2) {
                            if (combinaciones[j] == 0) {
                                combinaciones[j] = 2;
                                casilla = j;
                                conseguido = true;
                                break;
                            }
                        }
                    }
                }

                if (!conseguido) {

                    if (dificultad == 1) {
                        selection = new Random(arrayCasillas.size());

                        // Elegimos un índice al azar, entre 0 y el número de casillas libres que quedan
                        randomIndex = selection.nextInt(arrayCasillas.size());
                        casilla = arrayCasillas.get(randomIndex);
                        combinaciones[casilla] = 2;

                        Log.d("W", "Entra en Random");
                    } else {

                        if (combinaciones[0] == 0) {
                            combinaciones[0] = 2;
                            casilla = 0;
                        } else if (combinaciones[2] == 0) {
                            combinaciones[2] = 2;
                            casilla = 2;
                        } else if (combinaciones[6] == 0) {
                            combinaciones[6] = 2;
                            casilla = 6;
                        } else if (combinaciones[8] == 0) {
                            combinaciones[8] = 2;
                            casilla = 8;
                        }
                    }

                }
                for (int i = 0; i < 9; i++) {
                    Log.d("W", "combinaciones[" + i + "]:" + combinaciones[i]);
                }

            }


        }

        Log.d("W", "Posición:" + casilla);
        return casilla;
    }

}











