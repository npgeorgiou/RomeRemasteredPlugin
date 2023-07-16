package rr.language

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class RRFileType private constructor() : LanguageFileType(RRLanguage) {
    override fun getName(): String {
        return "RomeRemastered File"
    }

    override fun getDescription(): String {
        return "RomeRemastered language file"
    }

    override fun getDefaultExtension(): String {
        return "txt"
    }

    override fun getIcon(): Icon? {
        return RRIcons.FILE
    }

    companion object {
        @JvmField
        val INSTANCE = RRFileType()
    }
}