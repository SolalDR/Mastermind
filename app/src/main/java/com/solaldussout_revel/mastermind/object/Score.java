package com.solaldussout_revel.mastermind.object;



public class Score {
    private String[] reference;
    private String[] actual;
    private Integer countGoodPosition;
    private Integer countBadPosition;

    public Score(String[] ref, String[] act){
        System.out.println("-------INSTANCE DE SCORE--------");
        if(ref.length == act.length && ref.length == 4){
            System.out.println("Ref");
            for(int i=0; i<act.length; i++){
                System.out.println(ref[i]);
                System.out.println("---------------");
            }
            System.out.println("ACTUAL");
            for(int i=0; i<act.length; i++){
                System.out.println(act[i]);
                System.out.println("---------------");
            }
            this.setReference(ref);
            this.setActual(act);
            this.compare();
        }
    }

    //////////////////////////////////////////////////////
    //
    //                  ACTION
    //
    //////////////////////////////////////////////////////

    private void compare(){
        String[] ref = getReference();
        String[] act = getActual();

        String[] tmpRef = new String[ref.length];
        String[] tmpAct = new String[act.length];

        for(int i=0; i<tmpRef.length; i++){
            tmpRef[i] = ref[i];
            tmpAct[i] = act[i];
        }

        Integer goodPlace = 0;
        Integer badPlace = 0;

        for(int i=0; i<tmpRef.length; i++){
            for(int j=0; j<tmpAct.length; j++){
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


    //////////////////////////////////////////////////////
    //
    //                  GETTERS & SETTERS
    //
    //////////////////////////////////////////////////////

    private String[] getReference() {
        return reference;
    }

    private void setReference(String[] reference) {
        this.reference = reference;
    }

    private String[] getActual() {
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
