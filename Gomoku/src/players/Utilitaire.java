/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package players;

/**
 *
 * @author p1002239
 */
public class Utilitaire
{
    static int monRandom(int min, int max)
    {
        if(min > max) // Si min > max : swap min et max
        {
            int temp_min = min;
            min = max;
            max = temp_min;
        }
        
        return (int)(Math.random() * (max - min) + min);
    }
}
