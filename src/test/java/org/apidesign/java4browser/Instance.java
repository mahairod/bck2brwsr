/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apidesign.java4browser;

/**
 *
 * @author Jaroslav Tulach <jtulach@netbeans.org>
 */
public class Instance {
    private int i;
    protected short s;
    public double d;
    private float f;
    protected byte b = (byte)31;
    
    private Instance() {
    }

    public Instance(int i, double d) {
        this.i = i;
        this.d = d;
    }
    public byte getByte() {
        return b;
    }
    
    public void setByte(byte b) {
        this.b = b;
    }
    public static double defaultDblValue() {
        Instance create = new Instance();
        return create.d;
    }
    
    public static byte assignedByteValue() {
        return new Instance().b;
    }
    public static double magicOne() {
        Instance i = new Instance(10, 3.3d);
        i.b = (byte)0x09;
        return (i.i - i.b) * i.d;
    }
    public static int virtualBytes() {
        Instance i = new InstanceSub(7, 2.2d);
        i.setByte((byte)0x0a);
        Instance i2 = new Instance(3, 333.0d);
        i2.setByte((byte)44);
        return i.getByte() + i2.getByte();
    }
    public static float interfaceBytes() {
        GetByte i = new InstanceSub(7, 2.2d);
        return i.getByte();
    }
    public static boolean instanceOf(boolean sub) {
        Instance i = createInstance(sub);
        return isInstanceSubOf(i);
    }
    private static boolean isInstanceSubOf(Instance instance) {
        return instance instanceof InstanceSub;
    }
    private static Instance createInstance(boolean sub) {
        return sub ? new InstanceSub(3, 0) : new Instance();
    }
    private static boolean isNull() {
        return createInstance(true) == null;
    }
}
