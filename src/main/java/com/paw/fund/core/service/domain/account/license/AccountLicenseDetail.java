package com.paw.fund.core.service.domain.account.license;

import com.paw.fund.core.service.domain.account.license.content.AccountLicenseContent;
import com.paw.fund.core.service.domain.license.section.LicenseTemplateSection;
import com.paw.fund.core.service.domain.license.template.LicenseTemplate;
import com.paw.fund.core.service.domain.media.info.MediaInfoList;
import lombok.Builder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
public record AccountLicenseDetail(
        Long accountLicenseId,
        Long licenseTemplateId,
        String title,
        String description,
        MediaInfoList media,
        List<SectionContentDetail> licenseContent
) {
    public static List<SectionContentDetail> of(List<AccountLicenseContent> contents) {
        return SectionContentDetail.ContentDetail.of(contents)
                .stream()
                .collect(Collectors.groupingBy(SectionContentDetail.ContentDetail::sectionContent))
                .entrySet()
                .stream()
                .map(entry -> SectionContentDetail.builder()
                        .sectionContent(entry.getKey())
                        .contents(entry.getValue())
                        .build())
                .toList();
    }

    @Builder
    public record SectionContentDetail(
            String sectionContent,
            List<ContentDetail> contents
    ) {
        @Builder
        public record ContentDetail(
                Long accountLicenseContentId,
                String sectionContent,
                String licenseTemplateContent,
                String licenseContent
        ) {
            public static List<ContentDetail> of(List<AccountLicenseContent> contents) {
                return contents.stream()
                        .map(content -> ContentDetail.builder()
                                .accountLicenseContentId(content.accountLicenseContentId())
                                .sectionContent(content.content().sectionContent())
                                .licenseTemplateContent(content.content().licenseTemplateContent())
                                .licenseContent(content.content().licenseContent())
                                .build())
                        .toList();
            }

        }
    }

    public static AccountLicenseDetail of(AccountLicense accountLicense) {
        return AccountLicenseDetail.builder()
                .accountLicenseId(accountLicense.accountLicenseId())
                .title(accountLicense.title())
                .description(accountLicense.description())
                .media(accountLicense.media())
                .licenseContent(AccountLicenseDetail.of(accountLicense.contents()))
                .build();
    }
}
