package com.paw.fund.core.service.domain.account.license;

import com.paw.fund.core.service.domain.account.license.content.AccountLicenseContent;
import com.paw.fund.core.service.domain.license.section.LicenseTemplateSection;
import com.paw.fund.core.service.domain.license.section.content.TemplateSectionContent;
import com.paw.fund.core.service.domain.license.template.LicenseTemplate;
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
        List<LicenseSectionDetail> sections
) {

    @Builder
    public record LicenseSectionDetail(
            Long licenseTemplateSectionId,
            String sectionTitle,
            List<SectionContentDetail> contents
    ) {
        @Builder
        public record SectionContentDetail(
                Long accountLicenseContentId,
                Long templateSectionContentId,
                String sectionContent,
                String licenseContent
        ) {

            public static List<SectionContentDetail> from(List<TemplateSectionContent> sectionContents, List<AccountLicenseContent> contents) {
                Map<Long, AccountLicenseContent> contents1 = contents.stream()
                        .collect(Collectors.toMap(AccountLicenseContent::templateSectionContentId, ac -> ac));
                return sectionContents.stream()
                        .map(sectionContent -> {

                            AccountLicenseContent contents2 = contents1
                                    .computeIfAbsent(sectionContent.templateSectionContentId(), sc
                                            -> AccountLicenseContent.builder().build());
                            return SectionContentDetail.builder()
                                    .sectionContent(sectionContent.content())
                                    .licenseContent(contents2.content())
                                    .accountLicenseContentId(contents2.accountLicenseContentId())
                                    .templateSectionContentId(sectionContent.templateSectionContentId())
                                    .build();
                        })
                        .toList();
            }

        }
        public static List<LicenseSectionDetail> from(List<LicenseTemplateSection> templateSections, List<AccountLicenseContent> contents) {
            return templateSections.stream()
                    .map(templateSection -> LicenseSectionDetail.builder()
                            .licenseTemplateSectionId(templateSection.licenseTemplateSectionId())
                            .sectionTitle(templateSection.sectionTitle())
                            .contents(SectionContentDetail.from(templateSection.contents(), contents))
                            .build())
                    .toList();
        }
    }

    public static AccountLicenseDetail from(LicenseTemplate licenseTemplate, AccountLicense accountLicense) {
        return AccountLicenseDetail.builder()
                .accountLicenseId(accountLicense.accountLicenseId())
                .licenseTemplateId(licenseTemplate.licenseTemplateId())
                .title(licenseTemplate.title())
                .description(licenseTemplate.description())
                .sections(LicenseSectionDetail.from(licenseTemplate.sections(), accountLicense.contents()))
                .build();
    }
}
