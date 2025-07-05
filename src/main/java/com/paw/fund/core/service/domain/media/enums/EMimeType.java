package com.paw.fund.core.service.domain.media.enums;

import com.paw.fund.core.service.bootstrap.utils.PObjectUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.tika.Tika;

import java.io.InputStream;
import java.net.URI;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EMimeType {
    INSTANCE("", "", ""),

    JPEG("image/jpeg", "IMAGE","JPEG"),
    PNG("image/png", "IMAGE", "PNG"),
    GIF("image/gif", "IMAGE", "GIF"),
    WEBP("image/webp", "IMAGE", "WEBP"),
    BMP("image/bmp", "IMAGE", "BMP"),
    TIFF("image/tiff", "IMAGE", "TIFF"),
    ICO("image/x-icon", "IMAGE", "ICO(ICON)"),
    SVG("image/svg+xml", "IMAGE", "SVG"),
    HEIF("image/heif", "IMAGE", "HEIF (High Efficiency Image Format)"),
    HEIC("image/heic", "IMAGE", "HEIC (HEIF của Apple)"),

    MP4("video/mp4", "VIDEO", "MP4"),
    AVI("video/x-msvideo", "VIDEO", "AVI"),
    MKV("video/x-matroska", "VIDEO", "MKV"),
    WEBM("video/webm", "VIDEO", "WEBM"),
    MOV("video/quicktime", "VIDEO", "MOV"),
    THREEGP("video/3gpp", "VIDEO", "3GP"),
    OGV("video/ogg", "VIDEO", "OGV"),
    FLV("video/x-flv", "VIDEO", "FLV"),

    UNDEFINED("unknown", "UNDEFINED", "Undefined");

    String code;
    String type;
    String name;

    private Tika tika = new Tika();

    public EMimeType findMimeType(String url) {
        try (InputStream is = new URI(url).toURL().openStream()) {
            String mimeType = tika.detect(is);
            return Stream.of(values())
                    .filter(mimeType1 -> PObjectUtils.isEqual(mimeType1.getCode(), mimeType))
                    .findAny()
                    .orElse(UNDEFINED);
        } catch (Exception exc) {
            return EMimeType.UNDEFINED;
        }
    }
}
