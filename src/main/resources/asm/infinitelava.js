function initializeCoreMod() {
    return {
        'infinitelava': {
            'target': {
                'type': 'METHOD',
                'class': 'net.minecraft.fluid.LavaFluid',
                'methodName': 'func_205579_d', // canSourcesMultiply
                'methodDesc': '()Z'
            },
            'transformer': function(method) {
                var ASM = Java.type('net.minecraftforge.coremod.api.ASMAPI');
                var Opcodes = Java.type('org.objectweb.asm.Opcodes');
                var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');
                var InsnList = Java.type('org.objectweb.asm.tree.InsnList');
                var InsnNode = Java.type('org.objectweb.asm.tree.InsnNode');


                var newInstructions = new InsnList();

                                newInstructions.add(ASM.buildMethodCall(
                                    "com/tfar/finitewater/FiniteWater",
                                    "isLavaInfinite",
                                    "()Z",
                                    ASM.MethodType.STATIC
                                ));
                newInstructions.add(new InsnNode(Opcodes.IRETURN));
                method.instructions.insertBefore(method.instructions.getFirst(), newInstructions);

                return method;
            }
        }
    }
}
