package com.harm.unit.pattern.facade;

public class DoNumberTwo {
    private Toilet toilet;
    private Pants pants;
    private Underwear underwear;
    private Ass ass;
    public DoNumberTwo(Toilet toilet, Pants pants, Underwear underwear, Ass ass) {
        this.toilet = toilet;
        this.pants = pants;
        this.underwear = underwear;
        this.ass = ass;
    }
    public void bigShoot(int sequence) throws Exception {
        sequence = this.toilet.openCover(sequence);
        sequence = this.pants.lower(sequence);
        sequence = this.underwear.lower(sequence);
        sequence = this.ass.bigShot(sequence);
    }
}
