package com.api.hackweek.services;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranslationService {
    private static final String PORTUGUESE_LANGUAGE = "pt";

    public String translateToPortuguese(String text) {
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        Translation translation = translate.translate(text, Translate.TranslateOption.targetLanguage(PORTUGUESE_LANGUAGE));

        return translation.getTranslatedText();
    }
}
