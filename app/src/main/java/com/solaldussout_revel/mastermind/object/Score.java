package com.solaldussout_revel.mastermind.object;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Solal on 03/02/2017.
 */

public class Score {
    private String[] reference;
    private String[] actual;
    private Integer countGoodPosition;
    private Integer countBadPosition;

    public Score(String[] ref, String[] act){
        String[] referenceTab = new String[4];
        String[] actual = new String[4];

        for(int i=0; i<ref.length; i++){
            referenceTab[i] = ref[i];
        }
        for(int i=0; i<act.length; i++){
            actual[i] = act[i];
        }

        this.setReference(referenceTab);
        this.setActual(actual);
        this.setCounts();
    }

    public void setCounts(){
        String[] tmpRef = getReference();
        String[] tmpAct = getActual();
        Integer goodPlace = 0;
        Integer badPlace = 0;

        System.out.println("------------------");
        for(int i=0; i<tmpAct.length; i++){
            System.out.println(tmpRef[i]);
        }
        System.out.println("------------------");
        for(int i=0; i<tmpAct.length; i++){
            System.out.println(tmpAct[i]);
        }
        System.out.println("------------------");



        for(int i=0; i<tmpRef.length; i++){
            for(int j=0; j<tmpAct.length; j++){
                System.out.println(tmpAct[i]);
                System.out.println(tmpAct[j]);
                if(tmpRef[i] != null && tmpAct[j]!=null && tmpRef[i].equals(tmpAct[j]) && i == j){
                    goodPlace++;
                    tmpRef[i] = null;
                    tmpAct[j] = null;
                }
            }
        }

        for(int j=0; j<tmpAct.length; j++){
            for(int i=0; i<tmpRef.length; i++){
                if(tmpRef[i] != null && tmpAct[j]!=null && tmpRef[i].equals(tmpAct[j])&& tmpAct[j]!=null){
                    badPlace++;
                    tmpRef[i] = null;
                    tmpAct[j] = null;
                }
            }
        }

        this.setCountBadPosition(badPlace);
        this.setCountGoodPosition(goodPlace);
    }

    public String[] getReference() {
        return reference;
    }

    private void setReference(String[] reference) {
        this.reference = reference;
    }

    public String[] getActual() {
        return actual;
    }

    private void setActual(String[] actual) {
        this.actual = actual;
    }

    public Integer getCountGoodPosition() {
        return countGoodPosition;
    }

    private void setCountGoodPosition(Integer countGoodPosition) {
        this.countGoodPosition = countGoodPosition;
    }

    public Integer getCountBadPosition() {
        return countBadPosition;
    }

    private void setCountBadPosition(Integer countBadPosition) {
        this.countBadPosition = countBadPosition;
    }


}
