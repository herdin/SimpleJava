package com.harm.unit.pattern.facade;

import com.harm.unit.Unit;

public class FacadeStudy001 implements Unit {

    @Override
    public Object execute(Object[] obj) throws Exception {
        int sequence = -1;

        sequence = 0;
        sequence = new Toilet().openCover(sequence);
        sequence = new Pants().lower(sequence);
        sequence = new Underwear().lower(sequence);
        sequence = new Ass().bigShoot(sequence);

        sequence = 0;
        new DoNumberTwo(new Toilet(), new Pants(), new Underwear(), new Ass()).bigShoot(sequence);

        return null;
    }
}
