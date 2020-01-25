// This is a generated file. Not intended for manual editing.
package nl.hannahsten.texifyidea.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.StubBasedPsiElement;
import nl.hannahsten.texifyidea.index.stub.BibtexEntryStub;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface BibtexEntry extends PsiNameIdentifierOwner, StubBasedPsiElement<BibtexEntryStub> {

  @NotNull
  List<BibtexComment> getCommentList();

  @NotNull
  BibtexEndtry getEndtry();

  @Nullable
  BibtexEntryContent getEntryContent();

  @Nullable
  BibtexId getId();

  @Nullable
  BibtexPreamble getPreamble();

  @NotNull
  BibtexType getType();

  String getTitle();

  List<String> getAuthors();

  String getYear();

  String getIdentifier();

  String getAbstract();

  String getTagContent(String tagName);

  String getName();

  PsiElement setName(@NotNull @NonNls String name);

}
