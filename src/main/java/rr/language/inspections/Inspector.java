package rr.language.inspections;

import com.intellij.codeInspection.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rr.language.psi.RRFile;
import rr.language.psi.RRVisitor;

public class Inspector extends LocalInspectionTool {
    @NotNull
    @Override
    public final PsiElementVisitor buildVisitor(
        @NotNull ProblemsHolder holder,
        boolean isOnTheFly,
        @NotNull LocalInspectionToolSession session
    ) {
        RRFile file = ObjectUtils.tryCast(session.getFile(), RRFile.class);
        return file != null ? buildRRVisitor(holder, session) : new RRVisitor() ;
    }

    @NotNull
    @Override
    public final PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly) {
        throw new IllegalStateException();
    }

    @Nullable
    @Override
    public final ProblemDescriptor[] checkFile(@NotNull PsiFile file, @NotNull InspectionManager manager, boolean isOnTheFly) {
        throw new IllegalStateException();
    }

    @NotNull
    protected RRVisitor buildRRVisitor(@NotNull final ProblemsHolder holder, @NotNull LocalInspectionToolSession session) {
        return new RRVisitor() {
            @Override
            public void visitFile(PsiFile file) {
                checkFile((RRFile) file, holder);
            }
        };
    }

    protected void checkFile(@NotNull RRFile file, @NotNull ProblemsHolder problemsHolder) {
    }
}