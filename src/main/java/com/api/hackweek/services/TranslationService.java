package com.api.hackweek.services;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranslationService {
    private final Translate translate;

    public String translateToPortuguese(String text) {
        Translation translation = translate.translate(text);

        return translation.getTranslatedText();
    }
}
