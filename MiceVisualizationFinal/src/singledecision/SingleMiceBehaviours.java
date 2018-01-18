/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singledecision;


/**
 *
 * @author Christian
 */
public class SingleMiceBehaviours {
    public String behaviour;
    int testValue;
    String comparision; //less, greater, equal or null


    public SingleMiceBehaviours(String b, int t, String c) {
        behaviour = b;
        testValue = t;
        comparision = c;

    }

    public String getBehaviour() {
        return behaviour;
    }

    public int getTestValue() {
        return testValue;
    }

    public String getComparision() {
        return comparision;
    }

}