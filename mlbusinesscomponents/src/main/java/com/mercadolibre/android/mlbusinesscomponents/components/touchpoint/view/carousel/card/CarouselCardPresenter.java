package com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.view.carousel.card;

import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselCard;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselPill;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.CarouselTextFormat;
import com.mercadolibre.android.mlbusinesscomponents.components.touchpoint.domain.model.carousel.LogoImageFormat;
import com.mercadolibre.android.mlbusinesscomponents.components.utils.StringUtils;
import org.jetbrains.annotations.Nullable;

/* default */ class CarouselCardPresenter {

    /* default */ void onBind(final CarouselCard card, final CarouselCardView view) {
        setCardBackgroundColor(card.getBackgroundColor(), view);
        setImage(card.getImage(), view);
        setPill(card.getPill(), view);
        setTitle(card.getTopLabel(), card.getMainLabel(), card.getRightLabel(), view);
        setMainLabel(card.getSubtitle(), view);
        setTopLabel(card.getTitle(), view);
        setCardFormat(card.getTextColor(), view);
        setOnClickEvent(card.getLink(), view);
        setTitleFormat(card.getTitleFormat(), card.getTextColor(), view);
        SetSubtitleFormat(card.getSubtitleFormat(), card.getTextColor(), view);
        CofigureLogoOverlay(card.getImageFormat(), view);
        view.setTracking(card.getTracking());
    }

    private void CofigureLogoOverlay(final LogoImageFormat imageFormat, final CarouselCardView view) {
        if(imageFormat == null) {
            view.enableLogoOverlay();
            return;
        }

        if(imageFormat.isOverlay()) {
            view.enableLogoOverlay();
        } else {
            view.disableLogoOverlay();
        }
    }

    private void SetSubtitleFormat(final CarouselTextFormat subtitleFormat, final String fallbackColor, final CarouselCardView view) {
        if(subtitleFormat == null) {
            view.changeSubtitleFontStyleToDefault();
            return;
        }

        if (subtitleFormat.getWeight() != null &&  !subtitleFormat.getWeight().isEmpty()) {
            view.changeSubtitleFontStyle(subtitleFormat.getWeight());
        } else {
            view.changeSubtitleFontStyleToDefault();
        }

        if (subtitleFormat.getColor() != null && !subtitleFormat.getColor().isEmpty()  ) {
            view.changeSubtitleColor(subtitleFormat.getColor());
        } else {
            view.changeSubtitleColor(fallbackColor);
        }

        if (subtitleFormat.getSize() > 0) {
            view.changeSubtitleSize(subtitleFormat.getSize());
        } else {
            view.changeSubtitleSizeToDefault();
        }
    }

    private void setTitleFormat(final CarouselTextFormat titleFormat, final String fallbackColor,final CarouselCardView view) {
        if(titleFormat == null) {
            view.changeFontStyleToDefault();
            return;
        }

        if (titleFormat.getWeight() != null &&  !titleFormat.getWeight().isEmpty()) {
            view.changeTitleFontStyle(titleFormat.getWeight());
        } else {
            view.changeFontStyleToDefault();
        }

        if (titleFormat.getColor() != null && !titleFormat.getColor().isEmpty()  ) {
            view.changeTitleColor(titleFormat.getColor());
        } else {
            view.changeTitleColor(fallbackColor);
        }

        if (titleFormat.getSize() > 0) {
            view.changeTitleSize(titleFormat.getSize());
        } else {
            view.changeTitleSizeToDefault();
        }
    }

    private void setCardBackgroundColor(final String backgroundColor, final CarouselCardView view) {
        if (StringUtils.isValidString(backgroundColor)) {
            view.changeBackgroundColor(backgroundColor);
        } else {
            view.changeBackgroundColorToDefault();
        }
    }

    private void setOnClickEvent(final String link, final CarouselCardView view) {
        if (StringUtils.isValidString(link)) {
            view.onClick(link);
        }
    }

    private void setImage(final String image, final CarouselCardView view) {
        if (!StringUtils.isValidString(image)) {
            view.hideImage();
            return;
        }
        view.showImage(image);
    }

    private void setPill(@Nullable final CarouselPill pill, final CarouselCardView view) {
        if (pill == null) {
            view.hideLevel();
            return;
        }
        if (!StringUtils.isValidString(pill.getIcon())) {
            view.hideLevelIcon();
        } else {
            view.showLevelIcon(pill.getIcon());
            if (isValidPillFormat(pill)) {
                view.tintPillAsset(pill.getFormat().getTextColor());
            }
        }
        view.showLevelText(pill.getLabel(), pill.getFormat().getTextColor(),
            pill.getFormat().getBackgroundColor());
    }

    private void setTitle(final String topTitle, final String mainTitle, final String endTitle,
        final CarouselCardView view) {
        if (!StringUtils.isValidString(mainTitle)) {
            view.hideMainTitle();
        } else {
            view.showMainTitle(mainTitle);
        }
        if (!StringUtils.isValidString(endTitle)) {
            view.hideEndTitle();
        } else {
            view.showEndTitle(endTitle);
        }
        if (!StringUtils.isValidString(topTitle)) {
            view.hideTopTitle();
        } else {
            view.showTopTitle(topTitle);
        }
    }

    private void setTopLabel(final String topLabel, final CarouselCardView view) {
        if (!StringUtils.isValidString(topLabel)) {
            view.hideTopLabel();
        } else {
            view.showTopLabel(topLabel);
        }
    }

    private void setMainLabel(final String mainLabel, final CarouselCardView view) {
        if (!StringUtils.isValidString(mainLabel)) {
            view.hideMainLabel();
        } else {
            view.showMainLabel(mainLabel);
        }
    }

    private void setCardFormat(final String textColor, final CarouselCardView view) {
        if (StringUtils.isValidString(textColor)) {
            view.setTextColor(textColor);
        } else {
            view.setDefaultColor();
        }
    }

    private boolean isValidPillFormat(@Nullable final CarouselPill pill) {
        return pill != null && pill.getFormat() != null &&
            StringUtils.isValidString(pill.getFormat().getTextColor());
    }
}
