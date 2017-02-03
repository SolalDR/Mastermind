package com.solaldussout_revel.mastermind.object;

/**
 * Created by Solal on 03/02/2017.
 */

public class Score {
    private Long[] reference;
    private Long[] actual;
    private Integer countGoodPosition;
    private Integer countBadPosition;

    public Score(long[] ref, long[] act){
        Long[] referenceTab = new Long[4];
        Long[] actual = new Long[4];

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

    }

    public Long[] getReference() {
        return reference;
    }

    public void setReference(Long[] reference) {
        this.reference = reference;
    }

    public Long[] getActual() {
        return actual;
    }

    public void setActual(Long[] actual) {
        this.actual = actual;
    }

    public Integer getCountGoodPosition() {
        return countGoodPosition;
    }

    public void setCountGoodPosition(Integer countGoodPosition) {
        this.countGoodPosition = countGoodPosition;
    }

    public Integer getCountBadPosition() {
        return countBadPosition;
    }

    public void setCountBadPosition(Integer countBadPosition) {
        this.countBadPosition = countBadPosition;
    }
}
